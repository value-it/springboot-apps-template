#!/bin/sh

# 要コマンドインストール
# kubectl
# eksctl
# helm

export AWS_PROFILE=k8s-practice

export K8S_CLUSTER_NAME=bootapps-k8s
export REGION=ap-northeast-1
export NODES_SSH_PUBLIC_KEY=k8s-practice-key


# 1. 基本ネットワーク構築
aws cloudformation deploy \
--stack-name springboot-apps-k8s-template-network \
--template-file ./cloudformation/01-create-network.yaml

# 作成したSubnetから、タグForK8SPrivateCluster=trueがついているSubnetIDリストをカンマ区切りで取得
K8S_CLUSTER_SUBNETS=$(aws ec2 describe-subnets --filters "Name=tag:ForK8SPrivateCluster,Values=true" --query 'Subnets[].SubnetId | join(`","`, @)' --output=text)

echo "subnets: $K8S_CLUSTER_SUBNETS"

# K8Sクラスタ作成
eksctl create cluster \
  --name $K8S_CLUSTER_NAME \
  --region $REGION \
  --vpc-private-subnets $K8S_CLUSTER_SUBNETS \
  --with-oidc \
  --without-nodegroup

# CloudWatch Logs有効化
RES=`aws eks update-cluster-config \
  --region $REGION \
  --name $K8S_CLUSTER_NAME \
  --logging '{"clusterLogging":[{"types":["api","audit","authenticator","controllerManager","scheduler"],"enabled":true}]}'`

# ノードグループ作成
eksctl create nodegroup \
  --cluster $K8S_CLUSTER_NAME \
  --name $K8S_CLUSTER_NAME-ng1 \
  --node-private-networking \
  --node-type t3.small \
  --node-volume-size 50 \
  --node-volume-type gp2 \
  --nodes 2 \
  --nodes-min 2 \
  --nodes-max 2 \
  --ssh-access \
  --ssh-public-key $NODES_SSH_PUBLIC_KEY \
  --managed
# 一度失敗しても二回目やるとうまくいく？？
# 削除コマンド
# eksctl delete nodegroup --region=ap-northeast-1 --cluster=bootapps-k8s --name=bootapps-k8s-ng1


# ノードグループの一覧
eksctl get nodegroup --cluster $K8S_CLUSTER_NAME
