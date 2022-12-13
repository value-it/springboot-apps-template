package example.web.infrastructure.datasource.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookCatalogMapper {

  List<Book> findAll();

  void saveAsNew(Book book);

  Optional<Book> findById(Long id);

  void update(Book book);

  Long nextId();
}
