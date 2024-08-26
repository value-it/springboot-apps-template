package example.web.domain.model.bookcatalog.repository;

import example.web.domain.model.bookcatalog.*;

public interface BookCatalogRepository {

    BookList findAll();

    void saveAsNew(Book book);

    Book findById(BookId bookId);

    void update(Book book);

    BookId nextId();

    BookRevision nextRevision();

    Book findByName(Title title);
}
