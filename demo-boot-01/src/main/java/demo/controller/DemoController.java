package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping({"/", "/index"})
    public String myIndex() {
        return "Hello Spring Boot. ---- NEW ---one deploy 02 - slot";
    }

}
