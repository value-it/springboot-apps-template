package example.web.presentation.controller.bookcatalog.bookeditor;

import example.web.application.service.bookcatalog.BookFindService;
import example.web.application.service.bookcatalog.BookUpdateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookcatalog/editor")
public class BookEditController {

  private final BookUpdateService bookUpdateService;

  private final BookFindService bookFindService;

  public BookEditController(BookUpdateService bookUpdateService,
      BookFindService bookFindService) {
    this.bookUpdateService = bookUpdateService;
    this.bookFindService = bookFindService;
  }

  @GetMapping
  String index(@RequestParam("id") Long id, Model model) {
    model.addAttribute("bookEditForm", BookEditForm.fromDomainEntity(bookFindService.findById(id)));
    return "bookcatalog/editor";
  }

  @PostMapping
  String update(@Validated BookEditForm bookEditForm, BindingResult result) {

    if (result.hasErrors()) {
      return "bookcatalog/editor";
    }
    bookUpdateService.update(bookEditForm.toDomainEntity());
    return "redirect:/bookcatalog/list";
  }
}
