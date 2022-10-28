package com.productsquerymicroservice.microservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productsquerymicroservice.microservice.Models.ProductHistory;
import com.productsquerymicroservice.microservice.Services.DAOProductHistoryImpl;

@RestController
@RequestMapping("/product-history")
public class ProductHistoryRPC {
    @Autowired
    DAOProductHistoryImpl productHistoryImpl;

    public ProductHistory getProductDetailsOfCertainProduct(@RequestBody Long id){
        return productHistoryImpl.getProductDetailsHistory(id);
    }
}
