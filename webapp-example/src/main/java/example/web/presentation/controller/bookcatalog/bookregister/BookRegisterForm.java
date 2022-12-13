package example.web.presentation.controller.bookcatalog.bookregister;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.Isbn;
import example.web.domain.model.bookcatalog.Pages;
import example.web.domain.model.bookcatalog.Title;
import java.beans.ConstructorProperties;
import javax.validation.Valid;

public record BookRegisterForm(
    @Valid
    Title title,
    @Valid
    Isbn isbn,
    @Valid
    Pages pages
) {

  @ConstructorProperties({"title.value", "isbn.value", "pages.value"})
  public BookRegisterForm {
  }

  public Book toDomainEntity(Long id) {
    return new Book(id, this.title, this.isbn, this.pages);
  }

  public static BookRegisterForm of() {
    return new BookRegisterForm(new Title(""), new Isbn(""), new Pages("0"));
  }
}
