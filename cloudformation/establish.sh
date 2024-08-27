#!/bin/sh

if [ -z $CODESTAR_CONNECTION_ARN ]; then
  echo 'Please set the "CODESTAR_CONNECTION_ARN" environment variable.' >&2
  exit 1
fi

CURRENT_DIR=$(cd $(dirname $0); pwd)
cd $CURRENT_DIR

STAGE=dev

# 基本ネットワーク構築
aws cloudformation deploy \
--stack-name springboot-apps-template-network-$STAGE \
--template-file ./01-create-network.yaml \
|| exit 1

# 基本SecurityGroup作成
aws cloudformation deploy \
--stack-name springboot-apps-template-securitygroup-$STAGE \
--template-file ./02-securitygroup.yaml \
|| exit 1

# ALB作成
aws cloudformation deploy \
--stack-name springboot-apps-template-alb-$STAGE \
--template-file ./03.01.alb.yaml \
--parameter-overrides AlbNameSuffix=webapps-alb \
|| exit 1

# TargetGroupとListenerルール
# webapp-example
aws cloudformation deploy \
--stack-name springboot-apps-template-alb-tg-$STAGE \
--template-file ./cloudformation/03.02.alb.targetgroup.yaml \
--parameter-overrides ApplicationName=webapp-example \
  AlbNameSuffix=webapps-alb \
|| exit 1


# ECS定義作成
# ECS用Role
# 既存のServiceLinkedRoleが存在するか確認
ECS_ROLE_CREATE_OPTION=$(aws iam get-role --role-name "AWSServiceRoleForECS" > /dev/null 2>&1 && echo "CreateServiceLinkedRole=false" || echo "CreateServiceLinkedRole=true")
# ECS用Role等作成
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-base-$STAGE \
--template-file ./04.01.ecs.task.base.yaml \
--capabilities CAPABILITY_NAMED_IAM \
--parameter-overrides $ECS_ROLE_CREATE_OPTION \
|| exit 1

# ECSクラスター作成
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-cluster-$STAGE \
--template-file ./cloudformation/04.02.ecs.cluster.yaml \
|| exit 1

# ECSタスク定義
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-task-$STAGE \
--template-file ./04.03.ecs.task.def.yaml \
|| exit 1

# ECSサービス
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-service-$STAGE \
--template-file ./04.04.ecs.service.yaml \
|| exit 1

# 5. CI/CD定義作成
# Base
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-base-$STAGE \
--template-file ./05.01.ci.base.yaml \
--capabilities CAPABILITY_NAMED_IAM \
|| exit 1

# ECR
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-ecr-$STAGE \
--template-file ./05.02.ci.ecr.yaml \
|| exit 1

# CodeBuild
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-codebuild-$STAGE \
--template-file ./05.03.ci.codebuild.yaml \
|| exit 1

# CodeDeploy
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-codedeploy-$STAGE \
--template-file ./05.04.ci.codedeploy.yaml \
--parameter-overrides AlbNameSuffix=webapps-alb ApplicationName=webapp-example \
|| exit 1

# CodePipeline
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-codepipeline-$STAGE \
--template-file ./05.05.ci.codepipeline.yaml \
--parameter-overrides CodeStarConnectionArn=$CODESTAR_CONNECTION_ARN