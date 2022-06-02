package hola.models;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class Greetings {

    @GetMapping
    public String welcome(){
        return "welcome";
    }
}
