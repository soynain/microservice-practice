package com.inventorymicroservice.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="product_name")
    private String product_name;
    @Column(name="product_type")
    private String product_type;
    @Column(name="owner_brand")
    private String owner_brand;
    @Column(name="product_description")
    private String product_description;
    @Column(name="price_per_unit")
    private Double price_per_unit;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return this.product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getOwner_brand() {
        return this.owner_brand;
    }

    public void setOwner_brand(String owner_brand) {
        this.owner_brand = owner_brand;
    }

    public String getProduct_description() {
        return this.product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public Double getPrice_per_unit() {
        return this.price_per_unit;
    }

    public void setPrice_per_unit(Double price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

}
