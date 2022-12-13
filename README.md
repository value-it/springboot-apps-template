# SpringBootアプリ雛形

## 概要
- 簡単な書籍データベース
- ローカル用データベースはh2dbを使用（起動の都度初期化）
- DDDを意識した設計
- クラスは全てイミュータブル
- ValueObjectはレコードクラスで実装
- AWS(ECS)にデプロイ可能（CloudFormation添付）
  - [手順参照](./cloudformation/README.md)
- ECSへのデプロイ時はデフォルトではDB無効

## 前提

### 要Java17
https://aws.amazon.com/jp/corretto/

## サンプルWEBアプリ起動手順

### 起動コマンド
```shell
./tools/scripts/run-webapp
```

### URL
> http://localhost:38080/

