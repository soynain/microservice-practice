package com.cartandbuyms.cartandbuyms.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartandbuyms.cartandbuyms.Documents.SellsHistoryTransactionsCollection;
import com.cartandbuyms.cartandbuyms.Repositories.SellDbRepository;

@Service
public class SellDbServiceImpl implements SellDbService{
    @Autowired    
    private SellDbRepository sellDbRepository;

    @Override
    public SellsHistoryTransactionsCollection obtainUserByMysqlId(Long mysqlId) {
        return sellDbRepository.getProductByFkMysqlProductId(mysqlId);
    }

    @Override
    public List<SellsHistoryTransactionsCollection> getAllUsers() {
        return sellDbRepository.findAll();
    }

    

}
