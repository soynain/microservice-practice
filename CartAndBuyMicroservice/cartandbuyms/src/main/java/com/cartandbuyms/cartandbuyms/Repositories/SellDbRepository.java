package com.cartandbuyms.cartandbuyms.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cartandbuyms.cartandbuyms.Documents.SellsHistoryTransactionsCollection;

@Repository
public interface SellDbRepository extends MongoRepository<SellsHistoryTransactionsCollection,Long>{
    @Query("{}")
    public List<SellsHistoryTransactionsCollection> getAllProductDetails();
    @Query("{fk_mysql_id_product_individual_history:?0}")
    public SellsHistoryTransactionsCollection getProductByFkMysqlProductId(int fk_id);
}
