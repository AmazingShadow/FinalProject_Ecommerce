package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "/user/main";
    }

    @GetMapping("/cart")
    public String viewCart() {
        return "/user/cart";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @GetMapping("/product")
    public String product() {
        return "user/product1";
    }
    @GetMapping("/detail")
    public String detail() {
        return "user/detail";
    }
}
