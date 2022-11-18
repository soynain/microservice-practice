package com.cartandbuyms.cartandbuyms.Services;

import java.util.List;

import com.cartandbuyms.cartandbuyms.Documents.SellsHistoryTransactionsCollection;

public interface SellDbService {
    SellsHistoryTransactionsCollection obtainUserByMysqlId(Long mysqlId);
    List<SellsHistoryTransactionsCollection> getAllUsers();
}
