package example.web.domain.model.bookcatalog.service;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.BookId;
import example.web.domain.model.bookcatalog.Title;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookConsistencyService {
    private final BookCatalogRepository bookCatalogRepository;

    public BookConsistencyService(BookCatalogRepository bookCatalogRepository) {
        this.bookCatalogRepository = bookCatalogRepository;
    }

    public boolean isTitleExists(Title title) {
        return bookCatalogRepository.findByName(title).isPresent();
    }

    public boolean isTitleExists(Title title, BookId excludeId) {
        Optional<Book> book = bookCatalogRepository.findByName(title);
        return book.isPresent() && book.get().bookId().notEquals(excludeId);
    }
}
