package example.web.presentation.controller.bookcatalog.bookeditor;

import example.web.domain.model.bookcatalog.*;
import jakarta.validation.Valid;

import java.util.Objects;

public final class BookEditForm {
    @Valid
    private final BookId bookId;
    @Valid
    private final Title title;
    @Valid
    private final Isbn isbn;
    @Valid
    private final Pages pages;

    public BookEditForm(
            Long id,
            String title,
            String isbn,
            Integer pages
    ) {
        this.bookId = new BookId(id);
        this.title = new Title(title);
        this.isbn = new Isbn(isbn);
        this.pages = new Pages(pages);
    }

    public static BookEditForm fromDomainEntity(Book book) {
        return new BookEditForm(book.bookId().value(), book.title().value(), book.isbn().value(), book.pages().value());
    }

    public Book toDomainEntity(BookRevision revision) {
        return new Book(this.bookId, revision, this.title, this.isbn, this.pages);
    }

    public BookId bookId() {
        return bookId;
    }

    public Title title() {
        return title;
    }

    public Isbn isbn() {
        return isbn;
    }

    public Pages pages() {
        return pages;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BookEditForm) obj;
        return Objects.equals(this.bookId, that.bookId) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.isbn, that.isbn) &&
                Objects.equals(this.pages, that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, isbn, pages);
    }

    @Override
    public String toString() {
        return "BookEditForm[" +
                "bookId=" + bookId + ", " +
                "title=" + title + ", " +
                "isbn=" + isbn + ", " +
                "pages=" + pages + ']';
    }

}
