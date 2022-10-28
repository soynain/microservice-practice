package com.productsquerymicroservice.microservice.Models;

import java.sql.Date;

public class ProductHistory {
    private Long id;
    private Long fk_id_product_details_parent;
    private Date buy_date;
    private Long seller_id;
    private String product_id_bar_code_like;


    public ProductHistory(Long id, Long fk_id_product_details_parent, Date buy_date, Long seller_id, String product_id_bar_code_like) {
        this.id = id;
        this.fk_id_product_details_parent = fk_id_product_details_parent;
        this.buy_date = buy_date;
        this.seller_id = seller_id;
        this.product_id_bar_code_like = product_id_bar_code_like;
    }

    public ProductHistory(){}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFk_id_product_details_parent() {
        return this.fk_id_product_details_parent;
    }

    public void setFk_id_product_details_parent(Long fk_id_product_details_parent) {
        this.fk_id_product_details_parent = fk_id_product_details_parent;
    }

    public Date getBuy_date() {
        return this.buy_date;
    }

    public void setBuy_date(Date buy_date) {
        this.buy_date = buy_date;
    }

    public Long getSeller_id() {
        return this.seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public String getProduct_id_bar_code_like() {
        return this.product_id_bar_code_like;
    }

    public void setProduct_id_bar_code_like(String product_id_bar_code_like) {
        this.product_id_bar_code_like = product_id_bar_code_like;
    }

}
