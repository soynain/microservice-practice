package com.productsquerymicroservice.microservice.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.productsquerymicroservice.microservice.Models.ProductDetails;

public class ProductDetailsMapper implements RowMapper<ProductDetails>{

    @Override
    @Nullable
    public ProductDetails mapRow(ResultSet rs, int arg1) throws SQLException {
        ProductDetails productRow=new ProductDetails();
        productRow.setId(rs.getLong("id"));
        productRow.setProduct_name(rs.getString("product_name"));
        productRow.setProduct_description(rs.getString("product_description"));
        productRow.setProduct_type(rs.getString("product_type"));
        productRow.setOwner_brand(rs.getString("owner_brand"));
        return productRow;
    }
    
}
