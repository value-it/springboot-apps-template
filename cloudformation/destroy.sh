#!/bin/sh

# スタック全削除

STAGE=dev

# S3バケット削除
echo "delete s3 bucket"
# bootapps-tmpl-$STAGE-resources-base
RES=`aws s3api delete-objects --bucket bootapps-tmpl-$STAGE-resources-base \
--delete "$(aws s3api list-object-versions --bucket bootapps-tmpl-$STAGE-resources-base \
--query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}')"`
# バケット削除
aws s3 rb s3://bootapps-tmpl-$STAGE-resources-base --force

# bucket bootapps-tmpl-$STAGE-ecs-extras-base
RES=`aws s3api delete-objects --bucket bootapps-tmpl-$STAGE-ecs-extras-base \
--delete "$(aws s3api list-object-versions --bucket bootapps-tmpl-$STAGE-ecs-extras-base \
--query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}')"`
# バケット削除
aws s3 rb s3://bootapps-tmpl-$STAGE-ecs-extras-base --force

# bucket bootapps-tmpl-$STAGE-ecs-logs-base
RES=`aws s3api delete-objects --bucket bootapps-tmpl-$STAGE-ecs-logs-base \
--delete "$(aws s3api list-object-versions --bucket bootapps-tmpl-$STAGE-ecs-logs-base \
--query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}')"`
# バケット削除
aws s3 rb s3://bootapps-tmpl-$STAGE-ecs-logs-base --force

# bucket bootapps-tmpl-$STAGE-webapps-alb-logs-base
RES=`aws s3api delete-objects --bucket bootapps-tmpl-$STAGE-webapps-alb-logs-base \
--delete "$(aws s3api list-object-versions --bucket bootapps-tmpl-$STAGE-webapps-alb-logs-base \
--query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}')"`
# バケット削除
aws s3 rb s3://bootapps-tmpl-$STAGE-webapps-alb-logs-base --force

# ECRリポジトリ削除
echo "delete ecr repository"
aws ecr delete-repository --repository-name bootapps-tmpl/$STAGE/webapp-example --force

# ---

# CI CodePipeline
echo "delete-stack springboot-apps-template-ci-codepipeline-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codepipeline-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-codepipeline-$STAGE

# CI CodeDeploy
echo "delete-stack springboot-apps-template-ci-codedeploy-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codedeploy-$STAGE
# CI CodeBuild
echo "delete-stack springboot-apps-template-ci-codebuild-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codebuild-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-codebuild-$STAGE

# CI ECR
echo "delete-stack springboot-apps-template-ci-ecr-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-ecr-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-ecr-$STAGE

# CI Base
echo "delete-stack springboot-apps-template-ci-base-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-base-$STAGE

# ECSサービス
echo "delete-stack springboot-apps-template-ecs-service-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-service-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-service-$STAGE

# ECSタスク定義
echo "delete-stack springboot-apps-template-ecs-task-def-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-task-def-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-task-def-$STAGE

# ECSクラスター
echo "delete-stack springboot-apps-template-ecs-cluster-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-cluster-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-cluster-$STAGE

# ECS用Role
echo "delete-stack springboot-apps-template-ecs-base-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-base-$STAGE

# Green系TargetGroupのスタック削除でエラーにならないように予めリスナールールを消しておく
LISTENER_RULE_RPOD_ARN=$(aws cloudformation list-exports --query "Exports[?Name=='webapp-example-$STAGE-listener-rule-prod'].Value" --output text)
aws elbv2 delete-rule --rule-arn $LISTENER_RULE_RPOD_ARN
LISTENER_RULE_TEST_ARN=$(aws cloudformation list-exports --query "Exports[?Name=='webapp-example-$STAGE-listener-rule-test'].Value" --output text)
aws elbv2 delete-rule --rule-arn $LISTENER_RULE_TEST_ARN

# ALB(TargetGroup)
echo "delete-stack springboot-apps-template-alb-tg-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-alb-tg-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-alb-tg-$STAGE

# ALB
echo "delete-stack springboot-apps-template-alb-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-alb-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-alb-$STAGE

# 基本SecurityGroup
echo "delete-stack springboot-apps-template-securitygroup-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-securitygroup-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-securitygroup-$STAGE

# 基本ネットワーク
echo "delete-stack springboot-apps-template-network-$STAGE"
aws cloudformation delete-stack --stack-name springboot-apps-template-network-$STAGE
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-network-$STAGE
