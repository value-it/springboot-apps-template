#!/bin/sh

# スタック全削除

STAGE=dev

# ---

# CloudFormationスタックのExportから値を取得するFunction
get_exported_value (){
    export_name=$1
    aws cloudformation list-exports --query "Exports[?Name=='$export_name'].Value" --output text
}

# S3 バケット削除Function
remove_s3_bucket (){
    bucket_name=$1
    echo "Deleting S3 bucket: $bucket_name"
    # バージョニングされたオブジェクトの一覧を取得して削除
    versions=$(aws s3api list-object-versions --bucket "$bucket_name" --query='{Objects: Versions[].{Key:Key,VersionId:VersionId}}' --output=json)
    if [ "$versions" != "null" ] && [ "$versions" != "[]" ]; then
        echo "delete versions: $versions"
        aws s3api delete-objects --bucket "$bucket_name" --delete "$versions" > /dev/null 2>&1
    fi
    # 削除としてマークされたオブジェクトの一覧を取得して削除
    delete_markers=$(aws s3api list-object-versions --bucket "$bucket_name" --query='{Objects: DeleteMarkers[].{Key:Key,VersionId:VersionId}}' --output=json)
    if [ "$delete_markers" != "null" ] && [ "$delete_markers" != "[]" ]; then
        echo "delete markers: $delete_markers"
        aws s3api delete-objects --bucket "$bucket_name" --delete "$delete_markers" > /dev/null 2>&1
    fi
    # バケット削除
    aws s3 rb "s3://$bucket_name" --force
    echo "S3 bucket $bucket_name has been deleted."
}

# ---

# S3バケット削除
echo "delete s3 buckets"
remove_s3_bucket $(get_exported_value "S3Bucket-bootapps-tmpl-$STAGE-resources")
remove_s3_bucket $(get_exported_value "S3Bucket-bootapps-tmpl-$STAGE-ecs-extras")
remove_s3_bucket $(get_exported_value "S3Bucket-bootapps-tmpl-$STAGE-ecs-logs")
remove_s3_bucket $(get_exported_value "S3Bucket-bootapps-tmpl-$STAGE-webapps-alb-logs")

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
