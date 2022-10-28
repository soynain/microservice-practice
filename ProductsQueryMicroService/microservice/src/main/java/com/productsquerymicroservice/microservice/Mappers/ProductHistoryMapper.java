package com.productsquerymicroservice.microservice.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.productsquerymicroservice.microservice.Models.ProductHistory;

public class ProductHistoryMapper implements RowMapper<ProductHistory> {

    @Override
    @Nullable
    public ProductHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductHistory productHistory=new ProductHistory();
        productHistory.setFk_id_product_details_parent(rs.getLong("id_product_details_parent"));
        productHistory.setBuy_date(rs.getDate("buy_date"));
        productHistory.setSeller_id(rs.getLong("seller_id"));
        productHistory.setProduct_id_bar_code_like(rs.getString("product_id_bar_code_like"));
        return productHistory;
    }
    
}
