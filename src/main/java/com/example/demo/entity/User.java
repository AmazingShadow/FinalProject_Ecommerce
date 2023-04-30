package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    private String name;
    private String permissions;
    private String gender;
    private String office;
    private double salary;
    private Date birthday;
    private String address;
    private String email;

    @Column(unique = true)
    private String phone;
    private int active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @JsonManagedReference
    private Cart cart;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Orders> orders;

    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public User(String username, String password, String role, String phone, String name, String permissions, int active, Date createdAt, Date updatedAt) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.permissions = permissions;
        this.name = name;
        this.active = active;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
