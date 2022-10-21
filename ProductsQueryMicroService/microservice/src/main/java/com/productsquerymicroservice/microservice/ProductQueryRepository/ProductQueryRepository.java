package com.productsquerymicroservice.microservice.ProductQueryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.productsquerymicroservice.microservice.Mappers.ProductDetailsMapper;
import com.productsquerymicroservice.microservice.Models.ProductDetails;

@Repository
public class ProductQueryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductDetails> findAll(){
        return jdbcTemplate.query("select * from product_details;",new ProductDetailsMapper());
    }

    public ProductDetails findById(Long id){
        return jdbcTemplate.queryForObject("select * from product_details where id=?", new ProductDetailsMapper(),id);
    }
}
