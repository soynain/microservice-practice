package com.productsquerymicroservice.microservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productsquerymicroservice.microservice.DAOs.DAOProductHistory;
import com.productsquerymicroservice.microservice.Models.ProductHistory;
import com.productsquerymicroservice.microservice.ProductQueryRepository.ProductHistoryRepository;

@Service
public class DAOProductHistoryImpl implements DAOProductHistory{

    @Autowired
    ProductHistoryRepository productHistoryRepository;

    @Override
    public ProductHistory getProductDetailsHistory(Long id) {
        return productHistoryRepository.getProductDetailsOfProduct(id);
    }
    
}
