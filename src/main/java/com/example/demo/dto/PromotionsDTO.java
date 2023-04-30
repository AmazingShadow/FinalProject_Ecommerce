package com.example.demo.dto;

import java.sql.Date;

public class PromotionsDTO {
    private Long id;
    private Date dateEnd;
    private Date dateStart;
    private String name;
    private Double promotionLimit;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPromotionLimit() {
        return this.promotionLimit;
    }

    public void setPromotionLimit(Double promotionLimit) {
        this.promotionLimit = promotionLimit;
    }
}
