package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean deleteById(Long id);
    Page<User> findAllUserByOffice(Pageable pageable);
    Page<User> getAllCustomer(Pageable pageable);
    void saveEmployee(UserDTO user);
}
