package example.web.domain.model.bookcatalog;

import java.util.Optional;

public interface BookCatalogRepository {

  BookList findAll();

  void saveAsNew(Book book);

  Optional<Book> findById(Long id);

  void update(Book book);

  Long nextId();
}
