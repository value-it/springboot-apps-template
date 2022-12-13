package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Service;

@Service
public class BookUpdateService {

  private final BookCatalogRepository bookCatalogRepository;

  public BookUpdateService(BookCatalogRepository bookCatalogRepository) {
    this.bookCatalogRepository = bookCatalogRepository;
  }

  public void update(Book book) {
    bookCatalogRepository.update(book);
  }
}
