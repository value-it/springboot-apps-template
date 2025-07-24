package example.web.presentation.controller.bookcatalog.bookregister;

import example.web.domain.model.bookcatalog.book.Isbn;
import example.web.domain.model.bookcatalog.NewBook;
import example.web.domain.model.bookcatalog.book.Pages;
import example.web.domain.model.bookcatalog.book.Title;
import jakarta.validation.constraints.*;

public class BookRegisterForm {

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

    public BookRegisterForm(String title, String isbn, Integer pages) {
        this.title = title;
        this.isbn = isbn;
        this.pages = pages;
    }

    public static BookRegisterForm init() {
        return new BookRegisterForm("", "", 0);
    }

    public NewBook toDomainModel() {
        return new NewBook(
                new Title(title),
                new Isbn(isbn),
                new Pages(pages)
        );
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
