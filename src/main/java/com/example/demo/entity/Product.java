package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String brand;
    private String color;
    private String config;
    private String information;
    private String size;
    private String title;

    @Lob
    private String description;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DetailCart> detailCarts;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DetailOrders> detailOrders;
}
