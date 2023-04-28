package com.example.demo.api;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.response.ResponseObject;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

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
}
