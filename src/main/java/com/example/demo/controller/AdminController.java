package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String admin() {
        return "/admin/home-admin";
    }

    @GetMapping("/promo-code")
    public String promoCode() {
        return "/admin/promo-code";
    }

    @GetMapping("/category")
    public String category() {
        return "/admin/category";
    }

    @GetMapping("/product")
    public String product() {
        return "/admin/product";
    }

    @GetMapping("/staff")
    public String staff() {
        return "/admin/staff";
    }

    @GetMapping("/bill-mana")
    public String bills() {
        return "/admin/bills";
    }
}
