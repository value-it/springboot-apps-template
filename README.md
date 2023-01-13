# SpringBootアプリ雛形

## 概要
- 簡単な書籍データベース
- データベースはデフォルトではh2dbを使用（起動の都度初期化）
- DockerでPostgreSQLでも起動可能
- DDDを意識したパッケージ構造
- 全てのクラスがイミュータブル
- テーブル設計もイミュータブル
- モデルは可能な限りレコードクラスで実装
- AWS(ECS)にデプロイ可能（CloudFormation添付）
  - [手順参照](./cloudformation/README.md)


## 前提

### 要Java17
https://aws.amazon.com/jp/corretto/

## サンプルWEBアプリ起動手順

### アプリ起動コマンド
```shell
./tools/scripts/run-webapp
```

## （オプション）PostgreSQLで動かす場合
（要docker）
### DB起動
```shell
./tools/scripts/run-postgresql
```

### アプリ起動コマンド
```shell
./tools/scripts/run-webapp-with-postgresql
```


## URL
> http://localhost:38080/
