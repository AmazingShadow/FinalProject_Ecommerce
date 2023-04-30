package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
public class ProductDTO {
    private Long id;
    private String brand;
    private String color;
    private java.sql.Timestamp createdAt;
    private String description;
    private String image;
    private String name;
    private Double price;
    private Integer quantity;
    private java.sql.Timestamp updatedAt;
    private Long categoryId;
    private String config;
    private String information;
    private String size;
    private String title;
    private Double newPrice;

//    public Long getId() {
//        return this.id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getBrand() {
//        return this.brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getColor() {
//        return this.color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public java.sql.Timestamp getCreatedAt() {
//        return this.createdAt;
//    }
//
//    public void setCreatedAt(java.sql.Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getDescription() {
//        return this.description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImage() {
//        return this.image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getPrice() {
//        return this.price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Integer getQuantity() {
//        return this.quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public java.sql.Timestamp getUpdatedAt() {
//        return this.updatedAt;
//    }
//
//    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public Long getCategoryId() {
//        return this.categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getConfig() {
//        return this.config;
//    }
//
//    public void setConfig(String config) {
//        this.config = config;
//    }
//
//    public String getInformation() {
//        return this.information;
//    }
//
//    public void setInformation(String information) {
//        this.information = information;
//    }
//
//    public String getSize() {
//        return this.size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }
//
//    public String getTitle() {
//        return this.title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
}
