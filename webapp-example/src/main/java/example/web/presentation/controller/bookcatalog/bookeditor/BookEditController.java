package example.web.presentation.controller.bookcatalog.bookeditor;

import example.web.application.service.bookcatalog.BookFindService;
import example.web.application.service.bookcatalog.BookUpdateService;
import example.web.application.service.bookcatalog.BookConsistencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookcatalog/editor")
public class BookEditController {

    private final BookFindService bookFindService;
    private final BookUpdateService bookUpdateService;
    private final BookConsistencyService bookConsistencyService;

    public BookEditController(BookFindService bookFindService,
                              BookUpdateService bookUpdateService,
                              BookConsistencyService bookConsistencyService) {
        this.bookFindService = bookFindService;
        this.bookUpdateService = bookUpdateService;
        this.bookConsistencyService = bookConsistencyService;
    }

    @GetMapping
    String index(@RequestParam("id") Long id, Model model) {
        BookEditForm form = BookEditForm.of(bookFindService.findById(id));
        model.addAttribute("bookEditForm", form);
        return "bookcatalog/editor";
    }

    @PostMapping
    String update(@Validated BookEditForm bookEditForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bookcatalog/editor";
        }
        if (bookConsistencyService.isTitleExists(bookEditForm.title(), bookEditForm.id())) {
            result.rejectValue("title", "validation.book.title.duplicated");
            return "bookcatalog/editor";
        }
        bookUpdateService.update(bookEditForm.toDomainModel());
        return "redirect:/bookcatalog/list";
    }

}
