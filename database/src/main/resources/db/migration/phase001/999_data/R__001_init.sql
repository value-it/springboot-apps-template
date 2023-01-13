DELETE FROM bookcatalog.books_revision_latest;
DELETE FROM bookcatalog.books_revision;
DELETE FROM bookcatalog.books;


INSERT INTO bookcatalog.books(id)
VALUES (10001),
       (10002);

INSERT INTO bookcatalog.books_revision(book_id, revision, title, isbn, pages)
VALUES (10001, 1, 'エンジェルタロット', '9784866540689', 64),
       (10002, 2, '誰でもできる！Google for Education導入ガイド', '9784296070534', 344);

INSERT INTO bookcatalog.books_revision_latest(book_id, revision)
VALUES (10001,1),
       (10002,2);


ALTER SEQUENCE bookcatalog.seq_book_id RESTART WITH 10003;
ALTER SEQUENCE bookcatalog.seq_book_revision RESTART WITH 3;