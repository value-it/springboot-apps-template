package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.BookId;
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
        return BookEditForm.fromDomainEntity(bookFindService.findById(new BookId(id)));
    }

    public void update(BookEditForm form) {
        Book book = form.toDomainEntity(bookCatalogRepository.nextRevision());
        bookCatalogRepository.update(book);
    }
}
