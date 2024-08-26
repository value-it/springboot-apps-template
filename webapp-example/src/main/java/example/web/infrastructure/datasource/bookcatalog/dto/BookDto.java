package example.web.infrastructure.datasource.bookcatalog.dto;

import example.web.domain.model.bookcatalog.*;

public class BookDto {
    public Long bookId;
    public Long revision;
    public String title;
    public String isbn;
    public Integer pages;

    public Book toDomainModel() {
        return new Book(new BookId(bookId), new BookRevision(revision), new Title(title), new Isbn(isbn), new Pages(pages));
    }
}