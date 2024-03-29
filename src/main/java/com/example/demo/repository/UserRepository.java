package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("Select u from User as u where u.office not in ('Khách hàng')")
    Page<User> getAllUserByOffice(Pageable pageable);

    @Query("Select u from User as u where u.office = 'Khách hàng'")
    Page<User> getAllCustomer(Pageable pageable);
}
