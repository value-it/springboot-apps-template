package example.web.application.service.bookcatalog;

import example.web.domain.model.bookcatalog.Book;
import example.web.domain.model.bookcatalog.repository.BookCatalogRepository;
import example.web.presentation.controller.bookcatalog.bookregister.BookRegisterForm;
import org.springframework.stereotype.Service;

@Service
public class BookRegisterService {

    private final BookCatalogRepository bookCatalogRepository;

    public BookRegisterService(BookCatalogRepository bookCatalogRepository) {
        this.bookCatalogRepository = bookCatalogRepository;
    }

    public void saveAsNew(BookRegisterForm form) {
        Book book = form.toDomainEntity(bookCatalogRepository.nextId(), bookCatalogRepository.nextRevision());
        bookCatalogRepository.saveAsNew(book);
    }
}
