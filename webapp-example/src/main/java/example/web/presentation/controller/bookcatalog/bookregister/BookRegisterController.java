package example.web.presentation.controller.bookcatalog.bookregister;

import example.web.application.service.bookcatalog.BookRegisterService;
import example.web.application.service.bookcatalog.BookConsistencyService;
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
    private final BookConsistencyService bookConsistencyService;

    public BookRegisterController(BookRegisterService bookRegisterService, BookConsistencyService bookConsistencyService) {
        this.bookRegisterService = bookRegisterService;
        this.bookConsistencyService = bookConsistencyService;
    }

    @GetMapping
    String index(Model model) {
        model.addAttribute("bookRegisterForm", BookRegisterForm.init());
        return "bookcatalog/register";
    }

    @PostMapping
    String save(@Validated BookRegisterForm bookRegisterForm, BindingResult result) {
        if (result.hasErrors()) {
            return "bookcatalog/register";
        }
        if (bookConsistencyService.isTitleExists(bookRegisterForm.title())) {
            result.rejectValue("title", "validation.book.title.duplicated");
            return "bookcatalog/register";
        }
        bookRegisterService.saveAsNew(bookRegisterForm.toDomainModel());
        return "redirect:/bookcatalog/list";
    }
}
