package example.web.domain.model.bookcatalog.book;

import example.web.domain.model.bookcatalog.BookRevision;
import example.web.domain.model.bookcatalog.ModifyBook;
import example.web.domain.model.bookcatalog.NewBook;

public record Book(
        BookId bookId,
        BookRevision revision,
        Title title,
        Isbn isbn,
        Pages pages
) {

    public static Book of(NewBook newBook, BookId nextId, BookRevision nextRevision) {
        return new Book(
                nextId,
                nextRevision,
                newBook.title(),
                newBook.isbn(),
                newBook.pages()
        );
    }

    public static Book of(ModifyBook modifyBook, BookRevision nextRevision) {
        return new Book(
                modifyBook.bookId(),
                nextRevision,
                modifyBook.title(),
                modifyBook.isbn(),
                modifyBook.pages()
        );
    }
}
