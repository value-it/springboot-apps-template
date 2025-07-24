package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.book.Book;
import example.web.domain.model.bookcatalog.book.BookId;
import example.web.domain.model.bookcatalog.BookRevision;
import example.web.domain.model.bookcatalog.NewBook;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRegisterService {

    private final BookCatalogRepository bookCatalogRepository;

    public BookRegisterService(BookCatalogRepository bookCatalogRepository) {
        this.bookCatalogRepository = bookCatalogRepository;
    }

    public void saveAsNew(NewBook newBook) {
        BookId nextId = bookCatalogRepository.nextId();
        BookRevision nextRevision = bookCatalogRepository.nextRevision();
        Book book = Book.of(newBook, nextId, nextRevision);
        bookCatalogRepository.saveAsNew(book);
    }
}
