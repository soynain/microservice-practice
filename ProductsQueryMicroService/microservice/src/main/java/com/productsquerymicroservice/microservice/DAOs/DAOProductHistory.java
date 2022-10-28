package com.productsquerymicroservice.microservice.DAOs;

import com.productsquerymicroservice.microservice.Models.ProductHistory;

public interface DAOProductHistory {
    public ProductHistory getProductDetailsHistory(Long id);
}
