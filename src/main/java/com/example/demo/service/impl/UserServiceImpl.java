package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

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
        String password = passwordEncoder.encode("123456");
        String role;
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
                .username(user.getEmail())
                .password(password)
                .email(user.getEmail())
                .active(1)
                .birthday(user.getBirthday())
                .address(user.getAddress())
                .createdAt(new Date())
                .updatedAt(new Date())
                .phone(user.getPhone())
                .permissions("0")
                .role(role)
                .build();
        userRepository.save(employee);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
