package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.BookCatalogRepository;
import example.web.domain.model.bookcatalog.form.BookRegisterForm;
import org.springframework.stereotype.Service;

@Service
public class BookRegisterService {

  private final BookCatalogRepository bookCatalogRepository;

  public BookRegisterService(BookCatalogRepository bookCatalogRepository) {
    this.bookCatalogRepository = bookCatalogRepository;
  }

  public void saveAsNew(BookRegisterForm bookRegisterForm) {
    bookCatalogRepository.saveAsNew(
        bookRegisterForm.toDomainEntity(bookCatalogRepository.nextId()));
  }
}
