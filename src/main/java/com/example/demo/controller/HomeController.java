package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private HttpSession session;

    @Autowired
    private ProductService productService;
//    @Autowired
//    private CategoryService cartService;
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


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




    @GetMapping("/product")
    public String product(Model model, @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 6);
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        model.addAttribute("startPage", 0);
        return "user/product1";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
        }
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

    @GetMapping("/about")
    public String about() {
        return "user/about";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String signUp(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "username") String username,
                         @RequestParam(name = "pwd") String pwd, @RequestParam(name = "pwd_confirm") String pwd_confirm) {

        String errMessage = "";
        User new_user = userService.get(username);
        if(name.trim().isEmpty()) {
            errMessage = "Name is empty";
        }
        else if(username.trim().isEmpty()) {
            errMessage = "Username is empty";
        } else if(pwd.trim().isEmpty()) {
            errMessage = "Password is empty";
        } else if(pwd.trim().length() < 6) {
            errMessage = "Password must be at least 6 characters";
        } else if(!pwd_confirm.equals(pwd)) {
            errMessage = "Confirm password is not match";
        } else if(new_user != null) {
            errMessage = "This username already exists";
        }

        if(errMessage.length() > 0) {
            model.addAttribute("alert", errMessage);
            model.addAttribute("input_name", name);
            model.addAttribute("input_username", username);
            model.addAttribute("input_password", pwd);
            model.addAttribute("input_re_password", pwd_confirm);
            return "signup";
        } else {
            // Create cart
            Cart cart = new Cart();
            cart.setTotalPrice(0);
            cartService.save(cart);

            // Assign the new cart to the user
            // Create user
            User u = new User();
            Date date = new Date();
            u.setName(name);
            u.setUsername(username);
            u.setCreatedAt(date);
            u.setUpdatedAt(date);
            u.setRole("USER");
            u.setCart(cart);
            u.setPassword(passwordEncoder.encode(pwd));
            userService.save(u);

            session.setAttribute("user", u.getUsername());
            session.setAttribute("user_role", u.getRole());
            return "redirect:/login";
        }
    }
}
