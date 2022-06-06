package example.web.presentation.controller;

import example.web.application.example.ExampleService;
import example.web.domain.model.example.Example;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    String index(Model model) {

        Example hoge = exampleService.findExample();

        model.addAttribute("hoge", hoge);

        return "index";
    }

    private final ExampleService exampleService;

    public HomeController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
}
