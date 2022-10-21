package com.productsquerymicroservice.microservice.DAOs;

import java.util.List;

import com.productsquerymicroservice.microservice.Models.ProductDetails;

public interface DAOProductQuery {
    List<ProductDetails> getAllProducts();
    ProductDetails getProductById(Long id);
}
