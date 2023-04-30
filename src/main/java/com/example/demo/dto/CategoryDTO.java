package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String description;
    private String name;
    private Long promotion_id;

//    public Long getId() {
//        return this.id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
