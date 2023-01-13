package example.web.presentation.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    Log log = LogFactory.getLog(HomeController.class);

    @GetMapping
    String index(Model model) {

        log.info("index");

        model.addAttribute("ENV_TEST", System.getenv("ENV_TEST"));
        model.addAttribute("ENV_TEST_DEFAULT", System.getenv("ENV_TEST_default"));

        return "index";
    }
}
