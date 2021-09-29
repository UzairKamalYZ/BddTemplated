package be.techfix.bdd.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello there";
    }

    @GetMapping("/bye")
    public String goodBye(){
        return "Good Bye !!!";
    }
}
