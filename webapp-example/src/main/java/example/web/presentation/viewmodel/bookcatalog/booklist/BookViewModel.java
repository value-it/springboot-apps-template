package example.web.presentation.viewmodel.bookcatalog.booklist;

import example.web.domain.model.bookcatalog.book.Book;

public record BookViewModel(
        Long bookId,
        Long revision,
        String title,
        String isbn,
        Integer pages
) {
    public static BookViewModel of(Book book) {
        return new BookViewModel(
                book.bookId().value(),
                book.revision().value(),
                book.title().value(),
                book.isbn().value(),
                book.pages().value()
        );
    }
}
