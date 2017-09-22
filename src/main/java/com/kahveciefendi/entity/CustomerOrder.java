package com.kahveciefendi.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */

@Entity
public class CustomerOrder extends BaseEntity {


    private Customer customer;
    private List<Product> products;

    private String orderNote;
    private Double totalPrice;
    private Double discount;
    private Double finalTotalPrice;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "customer_order",
//            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID")
//    )
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinalTotalPrice() {
        return finalTotalPrice;
    }

    public void setFinalTotalPrice(Double finalTotalPrice) {
        this.finalTotalPrice = finalTotalPrice;
    }
}
