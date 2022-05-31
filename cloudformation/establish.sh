#!/bin/sh

if [ -z $CODESTAR_CONNECTION_ARN ]; then
  echo 'Please set the "CODESTAR_CONNECTION_ARN" environment variable.' >&2
  exit 1
fi


# 1. 基本ネットワーク構築
aws cloudformation deploy \
--stack-name springboot-apps-template-network \
--template-file ./01-create-network.yml


# 2. 基本SecurityGroup作成
aws cloudformation deploy \
--stack-name springboot-apps-template-securitygroup \
--template-file ./02-securitygroup.yaml 


3. ALB作成
aws cloudformation deploy \
--stack-name springboot-apps-template-alb \
--template-file ./03-alb.yml


4. ECS定義作成
# ECS用Role
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-role \
--template-file ./04.01.ecs.task.role.yaml \
--capabilities CAPABILITY_NAMED_IAM

# ECSタスク定義
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-task \
--template-file ./04.02.ecs.task.def.yaml

# ECSサービス
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-service \
--template-file ./04.03.ecs.service.yaml

# 5. CI/CD定義作成
# Base
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-base \
--template-file ./05.01.ci.base.yaml \
--capabilities CAPABILITY_NAMED_IAM

# ECR
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-ecr \
--template-file ./05.02.ci.ecr.yaml

# CodeBuild
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-codebuild \
--template-file ./05.03.ci.codebuild.yaml

# CodeDeploy
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-codedeploy \
--template-file ./05.04.ci.codedeploy.yaml

# CodePipeline
aws cloudformation deploy \
--stack-name springboot-apps-template-ci-codepipeline \
--template-file ./05.05.ci.codepipeline.yaml \
--parameter-overrides CodeStarConnectionArn=$CODESTAR_CONNECTION_ARN