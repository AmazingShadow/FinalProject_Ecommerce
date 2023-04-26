package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

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
    public String product(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
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

    @PostMapping("/product")
    public String addProduct(@RequestParam("file")MultipartFile file, @ModelAttribute ProductDTO product) {
        productService.saveProductToDatabase(file, product);
        return "redirect:/admin/product";
    }
}
