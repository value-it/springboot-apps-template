package example.web.infrastructure.datasource.bookcatalog;

import example.web.domain.model.bookcatalog.*;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import example.web.infrastructure.datasource.bookcatalog.dto.BookDto;
import org.springframework.stereotype.Repository;

@Repository
public class BookCatalogDataSource implements BookCatalogRepository {

    private final BookCatalogMapper mapper;

    public BookCatalogDataSource(BookCatalogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookList findAll() {
        return new BookList(
                mapper.findAll().stream()
                        .map(BookDto::toDomainModel)
                        .toList());
    }

    @Override
    public void saveAsNew(Book book) {
        BookDto bookDto = this.modelToDto(book);
        mapper.insertBook(bookDto);
        mapper.insertRevision(bookDto);
        mapper.deleteRevisionLatest(bookDto);
        mapper.insertRevisionLatest(bookDto);
    }

    @Override
    public Book findById(BookId bookId) {
        BookDto bookDto = mapper.findById(bookId.value());
        if (bookDto == null) {
            return null;
        }
        return mapper.findById(bookId.value()).toDomainModel();
    }

    @Override
    public void update(Book book) {
        BookDto bookDto = this.modelToDto(book);
        mapper.insertRevision(bookDto);
        mapper.deleteRevisionLatest(bookDto);
        mapper.insertRevisionLatest(bookDto);
    }

    @Override
    public BookId nextId() {
        return new BookId(mapper.nextId());
    }

    @Override
    public BookRevision nextRevision() {
        return new BookRevision(mapper.nextRevision());
    }

    @Override
    public Book findByName(Title title) {
        BookDto bookDto = mapper.findByName(title.value());
        if (bookDto == null) {
            return null;
        }
        return mapper.findByName(title.value()).toDomainModel();
    }

    private BookDto modelToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.bookId = book.bookId().value();
        bookDto.revision = book.revision().value();
        bookDto.title = book.title().value();
        bookDto.isbn = book.isbn().value();
        bookDto.pages = book.pages().value();
        return bookDto;
    }
}
