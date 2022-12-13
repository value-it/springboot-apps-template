package example.web.presentation.controller.bookcatalog;

import example.web.application.service.bookcatalog.BookUpdateService;
import example.web.domain.model.bookcatalog.form.BookEditForm;
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
public class BookEditorController {

  private final BookUpdateService bookUpdateService;

  public BookEditorController(BookUpdateService bookUpdateService) {
    this.bookUpdateService = bookUpdateService;
  }

  @GetMapping
  String index(@RequestParam("id") Long id, Model model) {
    model.addAttribute("bookEditForm", bookUpdateService.initializeBookEditForm(id));
    return "bookcatalog/editor";
  }

  @PostMapping
  String update(@Validated BookEditForm bookEditForm,
      BindingResult result, Model model) {

    if (!result.hasErrors()) {
      bookUpdateService.update(bookEditForm);
      return "redirect:/bookcatalog/list";
    }
//    model.addAttribute("bookEditForm", bookEditForm);
    return "bookcatalog/editor";
  }
}
