package com.kahveciefendi.entity;

import com.kahveciefendi.listener.EntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by hikuley on 22.09.2017.
 */

@Entity
@EntityListeners(value = EntityListener.class)
public class Product extends BaseEntity{

    private String name;
    private String description;

    @Column(name = "price", precision = 19, scale = 4, columnDefinition="Decimal(10,2) default '100.00'")
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }


}
