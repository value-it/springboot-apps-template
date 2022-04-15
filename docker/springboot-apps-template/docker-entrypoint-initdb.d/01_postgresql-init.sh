export PGUSER="$POSTGRES_USER";export PGPASSWORD="$POSTGRES_PASSWORD";psql example_db << EOF
  -- create user for readonly
  CREATE ROLE "example-user-readonly" LOGIN PASSWORD 'weak-password';
  GRANT CONNECT ON DATABASE example_db TO "example-user-readonly";
  GRANT USAGE ON SCHEMA public TO "example-user-readonly";
  ALTER DEFAULT PRIVILEGES FOR USER "example-user" GRANT SELECT ON TABLES TO "example-user-readonly";
EOF
