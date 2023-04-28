package com.example.demo.dto;

import lombok.Data;

@Data
public class CartDTO {
    private Long id;
    private Double totalPrice;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
