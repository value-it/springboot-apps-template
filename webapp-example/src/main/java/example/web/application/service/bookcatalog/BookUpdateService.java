package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.BookCatalogRepository;
import example.web.domain.model.bookcatalog.form.BookEditForm;
import org.springframework.stereotype.Service;

@Service
public class BookUpdateService {

  private final BookFindService bookFindService;
  private final BookCatalogRepository bookCatalogRepository;

  public BookUpdateService(BookFindService bookFindService,
      BookCatalogRepository bookCatalogRepository) {
    this.bookFindService = bookFindService;
    this.bookCatalogRepository = bookCatalogRepository;
  }

  public BookEditForm initializeBookEditForm(Long bookId) {
    return BookEditForm.fromDomainEntity(bookFindService.findById(bookId));
  }

  public void update(BookEditForm editor) {
    bookCatalogRepository.update(editor.toDomainEntity());
  }
}
