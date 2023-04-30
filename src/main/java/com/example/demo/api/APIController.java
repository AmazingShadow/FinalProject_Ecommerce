package com.example.demo.api;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.PromotionsDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.response.ResponseObject;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            Long category = product.get().getCategory().getId();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Successfully", "Chỉnh sửa" , category ,product)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Product is not found","Chỉnh sửa", null,"")
            );
        }
    }

    @PutMapping("/product/update/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
//        System.out.println(productDTO.toString());
        Product product = productService.findById(id).orElse(null);

        if (product != null) {
            if (productDTO.getBrand() != null) {
                // update form 1
                product.setBrand(productDTO.getBrand());
                product.setQuantity(productDTO.getQuantity());
                product.setInformation(productDTO.getInformation());
                product.setColor(productDTO.getColor());
                product.setConfig(productDTO.getConfig());
                product.setSize(productDTO.getSize());
            } else if (productDTO.getTitle() != null) {
                // update form 2
                product.setTitle(productDTO.getTitle());
                if (productDTO.getImage() != null) {
                    product.setImage(productDTO.getImage());
                }
                product.setDescription(productDTO.getDescription());
            } else if(productDTO.getName() != null) {
                // update form 3
                product.setName(productDTO.getName());
                product.setPrice(productDTO.getPrice());
                product.setCategory(Category.builder().id(productDTO.getCategoryId()).build());
            }
        } else {
            System.out.println("Khong tim thay san pham nay!");
        }
        productService.save(product);
    }

    // Delete user
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/staff";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseObject> getCustomer(@PathVariable("id") Long id) {
        Optional<User> customer = userService.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Successfully", "", null, customer)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Customer not found", "", null, "")
            );
        }
    }

    @PutMapping("/user/{id}")
    public void updateEmployee(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        User employee = userService.findById(id).orElse(null);
        String role;
        if (user.getOffice().equalsIgnoreCase("Quản lý")) {
            role = "QL";
        } else {
            role = "NV";
        }
        if (employee != null) {
            employee.setOffice(user.getOffice());
            employee.setSalary(user.getSalary());
            employee.setRole(role);
            userService.save(employee);
        } else {
            System.out.println("Khong tim thay user nay");
        }
    }

    @GetMapping("/promotion/{id}")
    public ResponseEntity<ResponseObject> getPromotion(@PathVariable("id") Long id) {
        Optional<Promotions> promotion = promotionService.findById(id);
        if (promotion.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Successfully", "Cập nhật mã khuyến mãi", null, promotion)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Promotion not found", "", null, "")
            );
        }
    }

    @DeleteMapping("/promotion/{id}")
    public void deletePromotion(@PathVariable("id") Long id) {
        promotionService.deleteById(id);
    }

    @PutMapping("/promotion/{id}")
    public void updatePromotion(@PathVariable("id") Long id, @RequestBody PromotionsDTO promotionsDTO) {
        Promotions promotions = promotionService.findById(id).orElse(null);
        if (promotions != null) {
            promotions.setPromotionLimit(promotionsDTO.getPromotionLimit());
            promotions.setDateStart(promotionsDTO.getDateStart());
            promotions.setDateEnd(promotionsDTO.getDateEnd());
            promotionService.save(promotions);
        } else {
            System.out.println("Promotion not found!");
        }

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseObject> getCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            Promotions promo = category.get().getPromotion();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Successfully", "Cập nhật danh mục", promo != null ? promo.getId() : null, category)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Category not found", "", null, "")
            );
        }
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.findById(id).orElse(null);
        Promotions promotion = promotionService.findById(categoryDTO.getPromotion_id()).orElse(null);
        if (category != null) {
            category.setDescription(categoryDTO.getDescription());
            category.setPromotion(Promotions.builder().id(categoryDTO.getPromotion_id()).build());
            categoryService.updateCategory(category);

            List<Product> products = productService.findProducts(category);
            System.out.println(products.size());
            if (promotion != null) {
                products.forEach(product -> {
                    product.setNewPrice(product.getPrice() - (product.getPrice() * promotion.getPromotionLimit()/100.0));
                    productService.save(product);
                });
            }
        } else {
            System.out.println("Category not found");
        }
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }

    @PostMapping("/addToCart/{id}")
    public void addToCart(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.findByUsername(username).getCart().getId();

        Product product = productService.findById(id).orElse(null);
        Cart cart = cartService.findById(userId).orElse(null);

        if (product != null && cart != null) {
            DetailCart detailCart = cartDetailService.findDetailCartByCartIdAndProductId(cart, product);
            if (detailCart != null) {
                detailCart.setQuantity(detailCart.getQuantity() + 1);
            } else {
                detailCart = new DetailCart();
                detailCart.setQuantity(1);
                detailCart.setCartId(cart);
                detailCart.setProductId(product);
            }
            // cart
            cart.setTotalPrice(cart.getTotalPrice() + 1 * product.getNewPrice());
            //
            cartDetailService.save(detailCart);
            cartService.save(cart);
        }

    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Long userId = userService.findByUsername(username).getCart().getId();
        Cart cart = cartService.findById(userId).orElse(null);
        Product product = productService.findById(id).orElse(null);

        if (cart != null && product != null) {
            DetailCart detailCart = cartDetailService.findDetailCartByCartIdAndProductId(cart, product);
            cart.setTotalPrice(cart.getTotalPrice() - detailCart.getQuantity() * product.getNewPrice());

            //
            cartDetailService.deleteById(detailCart.getId());
            cartService.save(cart);
        }
    }
}
