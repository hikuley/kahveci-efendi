package com.kahveciefendi.entity;

import com.kahveciefendi.listener.EntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(value = EntityListener.class)
public class OrderDone extends BaseEntity {


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double price;
    private Double discountPrice;
    private Double finalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_done_line",
            joinColumns = @JoinColumn(name = "order_done_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_line_id", referencedColumnName = "id")
    )
    private List<OrderLine> orderList;

    public OrderDone() {
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

    public List<OrderLine> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderLine> orderList) {
        this.orderList = orderList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
