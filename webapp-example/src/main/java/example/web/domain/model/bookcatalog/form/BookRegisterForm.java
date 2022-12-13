package example.web.domain.model.bookcatalog.form;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.Isbn;
import example.web.domain.model.bookcatalog.Title;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record BookRegisterForm(

    @Size(max = 50, message = "書籍名は50文字以内で入力してください")
    @NotEmpty(message = "書籍名を入力してください")
    String title,

    @Size(min = 13, max = 13, message = "ISBNは13桁で入力してください")
    @NotEmpty(message = "ISBNを入力してください")
    @Pattern(regexp = "[0-9]+", message = "数値で入力してください")
    String isbn,

    @Min(value = 1, message = "ページ数は1から10000の範囲で入力してください")
    @Max(value = 10000, message = "ページ数は1から10000の範囲で入力してください")
    @NotNull(message = "ページ数を入力してください")
    Integer pages
) {

  public Book toDomainEntity(Long id) {
    return new Book(id, new Title(this.title()), new Isbn(this.isbn()), this.pages());
  }

  public static BookRegisterForm of() {
    return new BookRegisterForm("", "", 0);
  }
}
