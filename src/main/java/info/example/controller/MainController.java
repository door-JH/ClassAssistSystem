package info.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/main")
    public String main(){

        log.info("sadf");

        return "main";
    }

    @GetMapping("/")
    public String intro(){
        return "redirect:/main";
    }

}
