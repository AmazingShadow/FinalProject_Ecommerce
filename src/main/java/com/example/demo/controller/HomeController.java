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
    @GetMapping("/admin")
    public String admin() {
        return "/admin/home-admin";
    }
    @GetMapping("/admin/promo-code")
    public String promoCode() {
        return "/admin/promo-code";
    }

    @GetMapping("/admin/category")
    public String category() {
        return "/admin/category";
    }
    @GetMapping("/admin/product")
    public String product() {
        return "/admin/product";
    }

    @GetMapping("/admin/staff")
    public String staff() {
        return "/admin/staff";
    }

    @GetMapping("/admin/bill-mana")
    public String bills() {
        return "/admin/bills";
    }
}
