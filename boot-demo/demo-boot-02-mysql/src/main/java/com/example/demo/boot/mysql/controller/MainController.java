package com.example.demo.boot.mysql.controller;

import com.example.demo.boot.mysql.entity.User;
import com.example.demo.boot.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;



    @Value("${qianjinEnv1:}")
    private String qianjinEnv1;
    @Value("${qianjinEnv2:}")
    private String qianjinEnv2;
    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @RequestMapping({"/", "/index"})
    public @ResponseBody String myIndex() {
        System.out.println("qianjin1" + " ---> " + System.getProperty("qianjin1"));
        System.out.println("qianjin2" + " ---> " + System.getProperty("qianjin2"));
        System.out.println("========================");
        System.out.println("qianjinEnv1" + " ---> " + qianjinEnv1);
        System.out.println("qianjinEnv2" + " ---> " + qianjinEnv2);
        System.out.println("qianjinEnv1 by System.getProperty" + " ---> " + System.getProperty("qianjinEnv1"));
        System.out.println("qianjinEnv1 by System.getEnv" + " ---> " + System.getenv("qianjinEnv1"));
        System.out.println("URL by System.getEnv AZURE_MYSQL_URL" + " ---> " + System.getenv("AZURE_MYSQL_URL"));
        System.out.println("URL by System.getEnv spring.datasource.url" + " ---> " + System.getenv("spring.datasource.url"));
        System.out.println("URL by System.getEnv spring.datasource.url by @Value" + " ---> " + springDatasourceUrl);
        return "Hello Spring Boot. ---- NEW ---one deploy 02 - slot";
    }


    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}