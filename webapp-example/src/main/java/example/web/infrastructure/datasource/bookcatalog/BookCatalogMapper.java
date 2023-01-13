package example.web.infrastructure.datasource.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.BookId;
import example.web.domain.model.bookcatalog.BookRevision;
import example.web.domain.model.bookcatalog.Title;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookCatalogMapper {

    List<Book> findAll();

    void insertBook(Book book);

    void insertRevision(Book book);

    void insertRevisionLatest(Book book);

    void deleteRevisionLatest(Book book);

    Optional<Book> findById(BookId bookId);

    BookId nextId();

    BookRevision nextRevision();

    Optional<Book> findByName(Title title);
}
