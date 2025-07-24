package example.web.presentation.controller.bookcatalog.booklist;

import example.web.application.service.bookcatalog.BookFindService;
import example.web.domain.model.bookcatalog.BookList;
import example.web.presentation.viewmodel.bookcatalog.booklist.BookListViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookcatalog/list")
public class BookListController {

    private final BookFindService bookFindService;

    public BookListController(BookFindService bookFindService) {
        this.bookFindService = bookFindService;
    }

    @GetMapping
    public String index() {
        return "bookcatalog/list";
    }

    @ModelAttribute("bookList")
    private BookListViewModel bookListViewModel() {
        BookList bookList = bookFindService.findAll();
        return BookListViewModel.of(bookList);
    }
}
