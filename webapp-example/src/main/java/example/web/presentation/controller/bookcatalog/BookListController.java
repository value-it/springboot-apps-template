package example.web.presentation.controller.bookcatalog;

import example.web.application.service.bookcatalog.BookFindService;
import example.web.domain.model.bookcatalog.BookList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  String index() {
    return "bookcatalog/list";
  }

  @ModelAttribute
  private BookList addAttributes(Model model) {
    return bookFindService.findAll();
  }
}
