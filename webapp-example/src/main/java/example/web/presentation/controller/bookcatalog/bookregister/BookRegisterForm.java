package example.web.presentation.controller.bookcatalog.bookregister;

import example.web.domain.model.bookcatalog.*;

import javax.validation.Valid;
import java.beans.ConstructorProperties;
import java.util.Objects;

public final class BookRegisterForm {
    @Valid
    private final Title title;
    @Valid
    private final Isbn isbn;
    @Valid
    private final Pages pages;

    @ConstructorProperties({"title.value", "isbn.value", "pages.value"})
    public BookRegisterForm(
            String title,
            String isbn,
            Integer pages
    ) {
        this.title = new Title(title);
        this.isbn = new Isbn(isbn);
        this.pages = new Pages(pages);
    }

    public static BookRegisterForm of() {
        return new BookRegisterForm("", "", 0);
    }

    public Book toDomainEntity(BookId bookId, BookRevision revision) {
        return new Book(bookId, revision, this.title, this.isbn, this.pages);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRegisterForm that = (BookRegisterForm) o;
        return title.equals(that.title) && isbn.equals(that.isbn) && pages.equals(that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, pages);
    }

    @Override
    public String toString() {
        return "BookRegisterForm{" +
                "title=" + title +
                ", isbn=" + isbn +
                ", pages=" + pages +
                '}';
    }
}
