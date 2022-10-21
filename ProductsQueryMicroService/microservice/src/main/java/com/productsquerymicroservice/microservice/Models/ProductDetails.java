package com.productsquerymicroservice.microservice.Models;

public class ProductDetails {
    private Long id;
    private String product_name;
    private String product_type;
    private String owner_brand;
    private String product_description;
    private String price_per_unit;


    public ProductDetails(Long id, String product_name, String product_type, String owner_brand, String product_description, String price_per_unit) {
        this.id = id;
        this.product_name = product_name;
        this.product_type = product_type;
        this.owner_brand = owner_brand;
        this.product_description = product_description;
        this.price_per_unit = price_per_unit;
    }

    public ProductDetails(){}


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

    public String getPrice_per_unit() {
        return this.price_per_unit;
    }

    public void setPrice_per_unit(String price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

}
