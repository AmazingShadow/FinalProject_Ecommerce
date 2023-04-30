package com.example.demo.entity;

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
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private double promotionLimit;

    @OneToMany(mappedBy = "promotion",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Category> categories;
}
