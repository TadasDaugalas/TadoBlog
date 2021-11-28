package com.example.tadoblog.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customLogin")
public class LoginController {

    @GetMapping
    public String loadPage(){
        return "login";
    }
}
