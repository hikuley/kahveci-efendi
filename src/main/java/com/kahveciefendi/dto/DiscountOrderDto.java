package com.kahveciefendi.dto;

import com.kahveciefendi.entity.OrderLine;

import java.util.List;

public class DiscountOrderDto {

    private Double price;
    private Double discountPrice;
    private Double finalPrice;
    private List<OrderLine> orderLines;

    public DiscountOrderDto() {

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
