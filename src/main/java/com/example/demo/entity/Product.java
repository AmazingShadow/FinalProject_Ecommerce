package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    private String name;
    private double price;
    private String brand;
    private String color;
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
