package example.web.presentation.controller.bookcatalog.bookeditor;

import example.web.application.service.bookcatalog.BookUpdateService;
import example.web.domain.model.bookcatalog.service.BookConsistencyService;
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
    private final BookConsistencyService bookConsistencyService;

    public BookEditController(BookUpdateService bookUpdateService, BookConsistencyService bookConsistencyService) {
        this.bookUpdateService = bookUpdateService;
        this.bookConsistencyService = bookConsistencyService;
    }

    @GetMapping
    String index(@RequestParam("id") Long id, Model model) {
        model.addAttribute("bookEditForm", bookUpdateService.createBookEditForm(id));
        return "bookcatalog/editor";
    }

    @PostMapping
    String update(@Validated BookEditForm bookEditForm, BindingResult result) {

        if (result.hasErrors()) {
            return "bookcatalog/editor";
        }
        if (bookConsistencyService.isTitleExists(bookEditForm.title(), bookEditForm.bookId())) {
            result.rejectValue("title.value", "validation.book.title.duplicated");
            return "bookcatalog/editor";
        }
        bookUpdateService.update(bookEditForm);
        return "redirect:/bookcatalog/list";
    }
}
