# SpringBootアプリ雛形

## 概要
- 簡単な書籍データベース
- データベースはデフォルトではh2dbを使用（起動の都度初期化）
- DDDを意識したレイヤードアーキテクチャのパッケージ構造
- ドメインモデルはイミュータブルで実装
- ドメインモデルは可能な限りレコードクラスで実装
- DBテーブルはすべてイミュータブル（UPDATE無しの設計）
- オプションとしてDockerでPostgreSQLでも起動可能
- CI/CD構成でAWS(ECS)に環境構築可能
  - [手順・CloudFormationコード](./cloudformation/README.md)

## 前提

### 要Java21
https://aws.amazon.com/jp/corretto/

## サンプルWEBアプリローカル起動手順

### アプリ起動
```shell
./tools/scripts/run-webapp
```

### URL
> http://localhost:38080/

### （オプション）PostgreSQLで動かす場合
### DB起動（要Docker）
```shell
./tools/scripts/run-postgresql
```

### アプリ起動
```shell
./tools/scripts/run-webapp-with-postgresql
```



