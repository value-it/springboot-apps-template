CREATE ROLE "example-user" LOGIN PASSWORD 'weak-password';

CREATE DATABASE example_db
    OWNER "example-user"
    ENCODING 'UTF-8'
    LC_COLLATE 'ja_JP.UTF-8'
    LC_CTYPE 'ja_JP.UTF-8'
    TEMPLATE template0;
