DROP SCHEMA IF EXISTS example CASCADE;

CREATE SCHEMA example;

SET
search_path TO example;

CREATE TABLE example
(
    id       character varying(20) primary key NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);
