package example.web.domain.model.bookcatalog.repository;

import example.web.domain.model.bookcatalog.*;
import example.web.domain.model.bookcatalog.book.Book;
import example.web.domain.model.bookcatalog.book.BookId;
import example.web.domain.model.bookcatalog.book.Title;

public interface BookCatalogRepository {

    BookList findAll();

    void saveAsNew(Book book);

    Book findById(BookId bookId);

    void update(Book book);

    BookId nextId();

    BookRevision nextRevision();

    Book findByName(Title title);
}
