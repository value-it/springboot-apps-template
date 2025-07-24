package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.book.Book;
import example.web.domain.model.bookcatalog.BookRevision;
import example.web.domain.model.bookcatalog.ModifyBook;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import example.web.presentation.controller.bookcatalog.bookeditor.BookEditForm;
import org.springframework.stereotype.Service;

@Service
public class BookUpdateService {

    private final BookCatalogRepository bookCatalogRepository;
    private final BookFindService bookFindService;

    public BookUpdateService(BookCatalogRepository bookCatalogRepository, BookFindService bookFindService) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.bookFindService = bookFindService;
    }

    public BookEditForm createBookEditForm(Long id) {
        return BookEditForm.of(bookFindService.findById(id));
    }

    public void update(ModifyBook modifyBook) {
        BookRevision nextRevision = bookCatalogRepository.nextRevision();
        Book book = Book.of(modifyBook, nextRevision);
        bookCatalogRepository.update(book);
    }
}
