package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRegisterService {

  private final BookCatalogRepository bookCatalogRepository;

  public BookRegisterService(BookCatalogRepository bookCatalogRepository) {
    this.bookCatalogRepository = bookCatalogRepository;
  }

  public void saveAsNew(Book book) {
    bookCatalogRepository.saveAsNew(book);
  }

  public Long nextId() {
    return bookCatalogRepository.nextId();
  }
}
