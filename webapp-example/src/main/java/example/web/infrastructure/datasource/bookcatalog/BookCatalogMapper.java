package example.web.infrastructure.datasource.bookcatalog;

import example.web.infrastructure.datasource.bookcatalog.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookCatalogMapper {

    List<BookDto> findAll();

    void insertBook(BookDto bookDto);

    void insertRevision(BookDto bookDto);

    void insertRevisionLatest(BookDto bookDto);

    void deleteRevisionLatest(BookDto bookDto);

    BookDto findById(Long bookId);

    BookDto findByName(String title);

    Long nextId();

    Long nextRevision();
}
