#!/bin/sh

# スタック全削除

# S3バケット削除
echo "delete s3 bucket"
# バージョニングオブジェクト全削除
RES=`aws s3api delete-objects --bucket bootapps-tmpl-resources-dev \
--delete "$(aws s3api list-object-versions --bucket bootapps-tmpl-resources-dev \
--query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}')"`
# バケット削除
aws s3 rb s3://bootapps-tmpl-resources-dev --force

# ECRリポジトリ削除
echo "delete ecr repository"
aws ecr delete-repository --repository-name bootapps-tmpl/application --force

# ---

# CI CodePipeline
echo "delete-stack springboot-apps-template-ci-codepipeline"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codepipeline
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-codepipeline

# CI CodeDeploy
echo "delete-stack springboot-apps-template-ci-codedeploy"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codedeploy
# CI CodeBuild
echo "delete-stack springboot-apps-template-ci-codebuild"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codebuild
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-codebuild

# CI ECR
echo "delete-stack springboot-apps-template-ci-ecr"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-ecr
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-ecr

# CI Base
echo "delete-stack springboot-apps-template-ci-base"
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-base

# ECSサービス
echo "delete-stack springboot-apps-template-ecs-service"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-service
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-service

# ECSタスク定義
echo "delete-stack springboot-apps-template-ecs-task"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-task
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-task

# ECS用Role
echo "delete-stack springboot-apps-template-ecs-role"
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-role

# ALB
echo "delete-stack springboot-apps-template-alb"
aws cloudformation delete-stack --stack-name springboot-apps-template-alb
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-alb

# 基本SecurityGroup
echo "delete-stack springboot-apps-template-securitygroup"
aws cloudformation delete-stack --stack-name springboot-apps-template-securitygroup
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-securitygroup

# 基本ネットワーク
echo "delete-stack springboot-apps-template-network"
aws cloudformation delete-stack --stack-name springboot-apps-template-network
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-network
