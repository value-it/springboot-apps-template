package example.web.domain.model.bookcatalog.repository;

import example.web.domain.model.bookcatalog.*;

import java.util.Optional;

public interface BookCatalogRepository {

    BookList findAll();

    void saveAsNew(Book book);

    Optional<Book> findById(BookId bookId);

    void update(Book book);

    BookId nextId();

    BookRevision nextRevision();

    Optional<Book> findByName(Title title);
}
