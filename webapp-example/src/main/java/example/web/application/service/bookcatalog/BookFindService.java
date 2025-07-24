package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.book.Book;
import example.web.domain.model.bookcatalog.book.BookId;
import example.web.domain.model.bookcatalog.BookList;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Service;

@Service
public class BookFindService {

    private final BookCatalogRepository bookCatalogRepository;

    public BookFindService(BookCatalogRepository bookCatalogRepository) {
        this.bookCatalogRepository = bookCatalogRepository;
    }

    public BookList findAll() {
        return bookCatalogRepository.findAll();
    }

    public Book findById(Long bookId) {
        return bookCatalogRepository.findById(new BookId(bookId));
    }
}
