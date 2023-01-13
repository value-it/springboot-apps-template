DROP SCHEMA IF EXISTS bookcatalog CASCADE;
CREATE SCHEMA bookcatalog;

DROP SEQUENCE IF EXISTS bookcatalog.seq_book_id;
DROP SEQUENCE IF EXISTS bookcatalog.seq_book_revision;

CREATE SEQUENCE bookcatalog.seq_book_id START WITH 10000 INCREMENT BY 1;
CREATE SEQUENCE bookcatalog.seq_book_revision START WITH 1 INCREMENT BY 1;

CREATE TABLE bookcatalog.books
(
    id         bigint                   NOT NULL DEFAULT nextval('bookcatalog.seq_book_id') primary key,
    created_at timestamp with time zone NOT NULL DEFAULT now()
);

CREATE TABLE bookcatalog.books_revision
(
    revision   bigint                   NOT NULL DEFAULT nextval('bookcatalog.seq_book_revision') primary key,
    book_id    bigint                   NOT NULL,
    title      character varying(50)    NOT NULL,
    isbn       character varying(13)    NOT NULL,
    pages      integer                  NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now()
);
ALTER TABLE bookcatalog.books_revision
    ADD CONSTRAINT fk_books_revision_book_id FOREIGN KEY (book_id) REFERENCES bookcatalog.books (id);
CREATE INDEX ON bookcatalog.books_revision (book_id);


CREATE TABLE bookcatalog.books_revision_latest
(
    book_id    bigint                   NOT NULL primary key,
    revision   bigint                   NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now()
);
ALTER TABLE bookcatalog.books_revision_latest
    ADD CONSTRAINT fk_books_revision_latest_book_id FOREIGN KEY (book_id) REFERENCES bookcatalog.books (id);
ALTER TABLE bookcatalog.books_revision_latest
    ADD CONSTRAINT fk_books_revision_latest_revision FOREIGN KEY (revision) REFERENCES bookcatalog.books_revision (revision);
ALTER TABLE bookcatalog.books_revision_latest
    ADD CONSTRAINT uq_ UNIQUE (revision);
CREATE INDEX ON bookcatalog.books_revision_latest (revision);
