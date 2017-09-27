package com.kahveciefendi.entity;

import com.kahveciefendi.listener.EntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */

@Entity
@EntityListeners(value = EntityListener.class)
public class OrderLine extends BaseEntity {


    private String orderNote;
    private Double price;
    private Long piece;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "order_addon",
            joinColumns = @JoinColumn(name = "order_line_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> addons;

    public OrderLine() {

    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getPiece() {
        return piece;
    }

    public void setPiece(Long piece) {
        this.piece = piece;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getAddons() {
        return addons;
    }

    public void setAddons(List<Product> addons) {
        this.addons = addons;
    }
}
