package com.productsquerymicroservice.microservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productsquerymicroservice.microservice.Models.ProductDetails;
import com.productsquerymicroservice.microservice.Services.DAOProductQueryImpl;

@RestController
@RequestMapping("/")
public class ProductQueryRPC {
    
    @Autowired
    DAOProductQueryImpl productQueryImpl;

    @GetMapping("/products/get-all")
    public List<ProductDetails> getProductsQuery(){
        return productQueryImpl.getAllProducts();
    }

    @GetMapping("/products/get/{id}")
    public ProductDetails getSpecificProduct(@PathVariable Long id){
        return productQueryImpl.getProductById(id);
    }
}
