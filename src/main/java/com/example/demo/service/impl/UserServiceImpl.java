package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<User> findAllUserByOffice(Pageable pageable) {
        return userRepository.getAllUserByOffice(pageable);
    }

    @Override
    public Page<User> getAllCustomer(Pageable pageable) {
        return userRepository.getAllCustomer(pageable);
    }

    @Override
    public void saveEmployee(UserDTO user) {
        String[] names = user.getName().trim().split("\\s+");
        String normalized = Normalizer.normalize(names[names.length-1], Normalizer.Form.NFD);
        String username = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String password = passwordEncoder.encode("123456");
        String role;
        String phone = user.getPhone().trim().substring(user.getPhone().length()-3, user.getPhone().length());

        if (user.getOffice().equalsIgnoreCase("Quản lý")) {
            role = "QL";
        } else {
            role = "NV";
        }

        User employee = User.builder()
                .name(user.getName())
                .gender(user.getGender())
                .salary(user.getSalary())
                .office(user.getOffice())
                .username(role + "_" + username + phone)
                .password(password)
                .active(1)
                .createdAt(new Date())
                .updatedAt(new Date())
                .phone(user.getPhone())
                .role(role)
                .build();
        userRepository.save(employee);
    }
}
