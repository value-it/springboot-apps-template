package example.web.presentation.controller;

import example.web.application.example.ExampleService;
import example.web.domain.model.example.Example;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    Log log = LogFactory.getLog(HomeController.class);

    @GetMapping
    String index(Model model) {

        log.info("index");

        Example hoge = exampleService.findExample();

        model.addAttribute("hoge", hoge);

        model.addAttribute("ENV_TEST", System.getenv("ENV_TEST"));
        model.addAttribute("ENV_TEST_DEFAULT", System.getenv("ENV_TEST_default"));
        model.addAttribute("ENV_TEST_V2", System.getenv("ENV_TEST_v2"));

        return "index";
    }

    private final ExampleService exampleService;

    public HomeController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
}
