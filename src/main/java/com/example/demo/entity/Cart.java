package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart")
    @JsonBackReference
    private User userId;
    private double totalPrice;
    @OneToMany(mappedBy = "cartId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<DetailCart> detailCarts;
}
