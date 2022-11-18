package com.cartandbuyms.cartandbuyms.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cartandbuyms.cartandbuyms.Documents.SellsHistoryTransactionsCollection;

@Repository
public interface SellDbRepository extends MongoRepository<SellsHistoryTransactionsCollection,Long>{
    @Query("{'fk_mysql_id_product_individual_history':?0}")
    public SellsHistoryTransactionsCollection getProductByFkMysqlProductId(Long fk_id);
}
