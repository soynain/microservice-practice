package com.productsquerymicroservice.microservice.ProductQueryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.productsquerymicroservice.microservice.Mappers.ProductHistoryMapper;
import com.productsquerymicroservice.microservice.Models.ProductHistory;

@Repository
public class ProductHistoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductHistory getProductDetailsOfProduct(Long id){
        return jdbcTemplate.queryForObject("select * from product_individual_history where fk_id_product_details_parent=?",new ProductHistoryMapper(),id);
    }


}
