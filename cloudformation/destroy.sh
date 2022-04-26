#!/bin/sh

# スタック全削除

# S3バケット削除
aws s3api delete-objects --bucket bootapps-tmpl-resources-dev \
--delete "$(aws s3api list-object-versions --bucket bootapps-tmpl-resources-dev \
--query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}')"

# ECRリポジトリ削除
aws ecr delete-repository --repository-name bootapps-tmpl/application --force

# ---

# CI CodePipeline
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codepipeline
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-codepipeline

# CI CodeDeploy
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codedeploy
# CI CodeBuild
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-codebuild
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-codebuild

# CI ECR
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-ecr
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ci-ecr

# CI Base
aws cloudformation delete-stack --stack-name springboot-apps-template-ci-base

# ECSサービス
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-service
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-service

# ECSタスク定義
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-task
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-ecs-task

# ECS用Role
aws cloudformation delete-stack --stack-name springboot-apps-template-ecs-role

# ALB
aws cloudformation delete-stack --stack-name springboot-apps-template-alb
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-alb

# 基本SecurityGroup
aws cloudformation delete-stack --stack-name springboot-apps-template-securitygroup
sleep 10
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-securitygroup

# 基本ネットワーク
aws cloudformation delete-stack --stack-name springboot-apps-template-network
aws cloudformation wait stack-delete-complete --stack-name springboot-apps-template-network
