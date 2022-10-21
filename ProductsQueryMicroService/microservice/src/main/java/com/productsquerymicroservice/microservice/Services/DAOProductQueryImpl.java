package com.productsquerymicroservice.microservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productsquerymicroservice.microservice.DAOs.DAOProductQuery;
import com.productsquerymicroservice.microservice.Models.ProductDetails;
import com.productsquerymicroservice.microservice.ProductQueryRepository.ProductQueryRepository;

@Service
public class DAOProductQueryImpl implements DAOProductQuery{
    @Autowired
    ProductQueryRepository repository;

    @Override
    public List<ProductDetails> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public ProductDetails getProductById(Long id) {
        return repository.findById(id);
    }
    
}
