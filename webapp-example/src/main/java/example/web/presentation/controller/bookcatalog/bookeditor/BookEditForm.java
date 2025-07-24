package example.web.presentation.controller.bookcatalog.bookeditor;

import example.web.domain.model.bookcatalog.*;
import example.web.domain.model.bookcatalog.book.*;
import jakarta.validation.constraints.*;

public class BookEditForm {

    @Min(value = 1, message = "IDが不正です")
    @NotNull(message = "IDが不正です")
    private final Long id;

    @Size(max = 50, message = "書籍名は50文字以内で入力してください")
    @NotEmpty(message = "書籍名を入力してください")
    private final String title;

    @Size(min = 13, max = 13, message = "ISBNは13桁で入力してください")
    @NotEmpty(message = "ISBNを入力してください")
    @Pattern(regexp = "[0-9]+", message = "ISBNは数値で入力してください")
    private final String isbn;

    @Min(value = 1, message = "ページ数は1以上の整数を入力してください")
    @Max(value = 10000, message = "ページ数は10000以内の整数を入力してください")
    @NotNull(message = "ページ数を入力してください")
    private final Integer pages;

    public BookEditForm(
            Long id,
            String title,
            String isbn,
            Integer pages
    ) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.pages = pages;
    }

    public static BookEditForm of(Book book) {
        return new BookEditForm(
                book.bookId().value(),
                book.title().value(),
                book.isbn().value(),
                book.pages().value());
    }

    public ModifyBook toDomainModel() {
        return new ModifyBook(
                new BookId(id),
                new Title(title),
                new Isbn(isbn),
                new Pages(pages)
        );
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String isbn() {
        return isbn;
    }

    public Integer pages() {
        return pages;
    }
}
