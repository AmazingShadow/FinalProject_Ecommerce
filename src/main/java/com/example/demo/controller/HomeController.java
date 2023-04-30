package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private HttpSession session;

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String index() {
        return "/user/main";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = 0L;
        int index = 0;
        // Truy vấn cơ sở dữ liệu để lấy id của username
        User user = userService.findByUsername(username);
        if (user != null) {
            userId = user.getCart().getId();
        }
//        Long userId = userService.findByUsername(username).getCart().getId();

        Cart cart = cartService.findById(userId).orElse(null);
        List<Product> products = cartDetailService.findAllProduct(cart);
        List<DetailCart> detailCarts = cartDetailService.findDetailCart(cart);
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            Product product1 = productService.findById(product.getId()).orElse(null);
            product1.setQuantity(detailCarts.get(index).getQuantity());
            productList.add(product1);
            index++;
        }
        model.addAttribute("products", productList);
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
        List<Category> categories = categoryService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
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
            return "user/signup";
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
            u.setActive(1);
            u.setOffice("Khách hàng");
            u.setPermissions("0");
            u.setRole("USER");
            u.setCart(cart);
            u.setPassword(passwordEncoder.encode(pwd));
            userService.save(u);

            session.setAttribute("user", u.getUsername());
            session.setAttribute("user_role", u.getRole());
            return "redirect:/login";
        }
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(value = "category", required = false) String category,
                               @RequestParam(value = "brand", required = false) String brand,
                               @RequestParam(value = "color", required = false) String color,
                               @RequestParam("page") Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.orElse(0), 6);
        List<Category> categories = categoryService.getAll();
        Category category1 = null;
        if (category != "") {
            category1 = categoryService.findById(Long.parseLong(category)).orElse(null);
        }
        Page<Product> products = null;
        ModelMap model = new ModelMap();
        int productPrice = 0;

        if (category == null && brand == null && color == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllProductSearch(brand, color, category1, pageable);
        }


        model.addAttribute("products", products);
        model.addAttribute("brand", brand);
        model.addAttribute("category", category);
        model.addAttribute("color", color);
        model.addAttribute("categories", categories);
        return new ModelAndView("user/product1", model);
    }
}
