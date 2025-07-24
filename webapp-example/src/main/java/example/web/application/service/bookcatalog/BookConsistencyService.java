package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.book.Book;
import example.web.domain.model.bookcatalog.book.BookId;
import example.web.domain.model.bookcatalog.book.Title;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Service;

@Service
public class BookConsistencyService {
    private final BookCatalogRepository bookCatalogRepository;

    public BookConsistencyService(BookCatalogRepository bookCatalogRepository) {
        this.bookCatalogRepository = bookCatalogRepository;
    }

    public boolean isTitleExists(String title) {
        return bookCatalogRepository.findByName(new Title(title)) != null;
    }

    public boolean isTitleExists(String title, Long excludeId) {
        Book book = bookCatalogRepository.findByName(new Title(title));
        return book != null && book.bookId().notEquals(new BookId(excludeId));
    }
}
