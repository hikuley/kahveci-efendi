package com.kahveciefendi.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */

@Entity
public class CustomerOrder extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String orderNote;
    private Double totalPrice;
    private Double totalDiscountPrice;
    private Double totalFinalPrice;

    @OneToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    public CustomerOrder() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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


    public Double getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(Double totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Double getTotalFinalPrice() {
        return totalFinalPrice;
    }

    public void setTotalFinalPrice(Double totalFinalPrice) {
        this.totalFinalPrice = totalFinalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
