DROP SCHEMA IF EXISTS bookcatalog CASCADE;
CREATE SCHEMA bookcatalog;

DROP SEQUENCE IF EXISTS "seq_book_id";
CREATE SEQUENCE "seq_book_id";

CREATE TABLE bookcatalog.books
(
    id         bigint                   NOT NULL DEFAULT nextval('seq_book_id') primary key,
    title      character varying(50)    NOT NULL,
    isbn       character varying(13)    NOT NULL,
    pages      integer                  NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now()
);
