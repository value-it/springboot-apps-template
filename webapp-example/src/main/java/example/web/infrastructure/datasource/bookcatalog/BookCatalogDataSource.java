package example.web.infrastructure.datasource.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.BookList;
import example.web.domain.model.bookcatalog.BookCatalogRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class BookCatalogDataSource implements BookCatalogRepository {

  @Override
  public BookList findAll() {
    return new BookList(mapper.findAll());
  }

  @Override
  public void saveAsNew(Book book) {
    mapper.saveAsNew(book);
  }

  @Override
  public Optional<Book> findById(Long id) {
    return mapper.findById(id);
  }

  @Override
  public void update(Book book) {
    mapper.update(book);
  }

  @Override
  public Long nextId() {
    return mapper.nextId();
  }

  private final BookCatalogMapper mapper;

  public BookCatalogDataSource(BookCatalogMapper mapper) {
    this.mapper = mapper;
  }
}
