package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private User userId;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DetailOrders> detailOrders;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
}
