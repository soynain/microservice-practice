package com.cartandbuyms.cartandbuyms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartandbuyms.cartandbuyms.Documents.SellsHistoryTransactionsCollection;
import com.cartandbuyms.cartandbuyms.Services.SellDbServiceImpl;

@RestController
@RequestMapping("/selldb")
public class SellDbController {
    @Autowired
    private SellDbServiceImpl sellDbServiceImpl;

    @GetMapping("/get-all")
    public ResponseEntity<List<SellsHistoryTransactionsCollection>> getAllProductTransactionDetails(){
        return ResponseEntity.ok().body(sellDbServiceImpl.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SellsHistoryTransactionsCollection> getSellDetailsMysqlFk(@PathVariable Long id){
        return ResponseEntity.ok().body(sellDbServiceImpl.obtainUserByMysqlId(id));
    }
}
