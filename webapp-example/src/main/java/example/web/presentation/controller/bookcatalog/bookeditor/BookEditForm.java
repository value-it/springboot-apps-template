package example.web.presentation.controller.bookcatalog.bookeditor;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.Isbn;
import example.web.domain.model.bookcatalog.Pages;
import example.web.domain.model.bookcatalog.Title;
import java.beans.ConstructorProperties;
import javax.validation.Valid;

public record BookEditForm(
    Long id,
    @Valid
    Title title,
    @Valid
    Isbn isbn,
    @Valid
    Pages pages
) {

  @ConstructorProperties({"id", "title.value", "isbn.value", "pages.value"})
  public BookEditForm {
  }

  public static BookEditForm fromDomainEntity(Book book) {
    return new BookEditForm(book.id(), book.title(), book.isbn(), book.pages());
  }

  public Book toDomainEntity() {
    return new Book(this.id, this.title, this.isbn, this.pages);
  }
}
