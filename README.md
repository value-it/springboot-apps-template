# SpringBootアプリ雛形

- dockerで起動したPostgreSQLから値を取得して表示するだけのサンプルプログラム

### 要Java17
https://aws.amazon.com/jp/corretto/

## 起動手順

### dockerでローカルDB(PostgreSQL)起動
```shell
./tools/scripts/run-postgresql
```

### ローカルDBマイグレーション実行（Flyway）
```shell
./tools/scripts/flyway-migrate

# 初期化してマイグレーションする場合は
./tools/scripts/flyway-clean-and-migrate
```

### サンプルWEBアプリ起動
```shell
./tools/scripts/run-webapp
```

### DB接続無しでサンプルWEBアプリ起動
```shell
./tools/scripts/run-webapp-without-db
```

---
### サンプルWEBアプリURL
> http://localhost:38080/


---


## （参考）dockerローカルDB接続情報
> DB名: example_db  
> Port: 55432
>
> SuperUser: postgres    
> UperUserPassword: postgres
>
> AppUser: example-user  
> AppUser Password: weak-password

