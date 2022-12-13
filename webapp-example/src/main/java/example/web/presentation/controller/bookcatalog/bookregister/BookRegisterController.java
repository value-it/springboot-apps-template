package example.web.presentation.controller.bookcatalog.bookregister;

import example.web.application.service.bookcatalog.BookRegisterService;
import example.web.domain.model.bookcatalog.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookcatalog/register")
public class BookRegisterController {

  private final BookRegisterService bookRegisterService;

  public BookRegisterController(BookRegisterService bookRegisterService) {
    this.bookRegisterService = bookRegisterService;
  }

  @GetMapping
  String index(Model model) {
    model.addAttribute("bookRegisterForm", BookRegisterForm.of());
    return "bookcatalog/register";
  }

  @PostMapping
  String save(@Validated BookRegisterForm bookRegisterForm, BindingResult result) {

    if (result.hasErrors()) {
      return "bookcatalog/register";
    }
    Book book = bookRegisterForm.toDomainEntity(bookRegisterService.nextId());
    bookRegisterService.saveAsNew(book);
    return "redirect:/bookcatalog/list";
  }
}
