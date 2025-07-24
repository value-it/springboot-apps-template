package example.web.domain.model.bookcatalog;

import example.web.domain.model.bookcatalog.book.BookId;
import example.web.domain.model.bookcatalog.book.Isbn;
import example.web.domain.model.bookcatalog.book.Pages;
import example.web.domain.model.bookcatalog.book.Title;

public record ModifyBook(
        BookId bookId,
        Title title,
        Isbn isbn,
        Pages pages
) {

}
