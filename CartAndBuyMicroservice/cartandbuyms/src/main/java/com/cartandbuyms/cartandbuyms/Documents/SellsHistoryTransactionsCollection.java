package com.cartandbuyms.cartandbuyms.Documents;

import java.util.Date;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection ="sells_history_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellsHistoryTransactionsCollection {
    @Id
    private long id;
    @Field
    private long fk_mysql_id_product_individual_history;
    @Field
    private long qty_given_by_client;
    @Field
    private long qty_change_given_back;
    @Field
    private int items_quantity;
    @Field
    private boolean is_in_cart;
    @Field
    private String final_sell_date;
}
