# CloudFormationでSpringBoot雛形アプリをBlue/Greenデプロイする環境を構築する手順

- AWSリソースの設定値はひとまず動かすことだけにフォーカスしたものなので、そのまま商用環境で利用しないこと
- AWSにデータベースまで構築すると大掛かりになりすぎるので、サーバー環境ではDB無しで動作するようにアプリを構成

## 事前準備

### AWS SecretsManagerに以下の値を登録
> 名前： SpringBootAppsTemplate  
> キー名: GitHubSecretToken  
> 値： Githubリポジトリへアクセスするための個人用アクセストークン

### AWS SystemsManagerのパラメータストアで以下の値を登録
> /SPRINGBOOT_APPS_TEMPLATE/DOCKERHUB_USERNAME： DockerHubユーザー名  
> /SPRINGBOOT_APPS_TEMPLATE/DOCKERHUB_PASSWORD： DockerHubパスワード  


```shell
# AWSアカウントのプロファイルを作業PCに作成して環境変数で指定（プロファイル名は適宜）
export AWS_PROFILE=hogehoge
```

## 1. 基本ネットワーク構築
```shell
aws cloudformation deploy \
--stack-name springboot-apps-template-network \
--template-file ./01-create-network.yml
```

## 2. 基本SecurityGroup作成
```shell
aws cloudformation deploy \
--stack-name springboot-apps-template-securitygroup \
--template-file ./02-securitygroup.yaml 
```

## 3. ALB作成
```shell
aws cloudformation deploy \
--stack-name springboot-apps-template-alb \
--template-file ./03-alb.yml
```

## 4. ECS定義作成
```shell
# ECS用Role
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-role \
--template-file ./04.01.ecs.task.role.yaml \
--capabilities CAPABILITY_NAMED_IAM

# ECSタスク定義
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-task \
--template-file ./04.02.ecs.task.def.yaml

# ECSサービス（一旦ハコだけつくるのでタスク数は0）
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-service \
--template-file ./04.03.ecs.service.yaml
```

## 5. CI/CD定義作成
```shell
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
--template-file ./05.05.ci.codepipeline.yaml
```



## ECSタスク数を1で起動
```shell
aws cloudformation deploy \
--stack-name springboot-apps-template-ecs-service \
--template-file ./04.03.ecs.service.yaml \
--parameter-overrides DesiredCount=1
```
