package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

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
    public String product(Model model, @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 6);
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        model.addAttribute("startPage", 0);
        return "user/product1";
    }
    @GetMapping("/detail")
    public String detail() {
        return "user/detail";
    }
    @GetMapping("/warranty")
    public String warranty() {
        return "user/warranty";
    }
    @GetMapping("/order")
    public String order() {
        return "user/order";
    }
}
