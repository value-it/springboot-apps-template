package example.web.infrastructure.datasource.bookcatalog;

import example.web.domain.model.bookcatalog.*;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookCatalogDataSource implements BookCatalogRepository {

    private final BookCatalogMapper mapper;

    public BookCatalogDataSource(BookCatalogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookList findAll() {
        return new BookList(mapper.findAll());
    }

    @Override
    public void saveAsNew(Book book) {
        mapper.insertBook(book);
        mapper.insertRevision(book);
        mapper.deleteRevisionLatest(book);
        mapper.insertRevisionLatest(book);
    }

    @Override
    public Optional<Book> findById(BookId bookId) {
        return mapper.findById(bookId);
    }

    @Override
    public void update(Book book) {
        mapper.insertRevision(book);
        mapper.deleteRevisionLatest(book);
        mapper.insertRevisionLatest(book);
    }

    @Override
    public BookId nextId() {
        return mapper.nextId();
    }

    @Override
    public BookRevision nextRevision() {
        return mapper.nextRevision();
    }

    @Override
    public Optional<Book> findByName(Title title) {
        return mapper.findByName(title);
    }
}
