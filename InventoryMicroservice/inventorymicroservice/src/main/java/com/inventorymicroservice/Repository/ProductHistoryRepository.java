package com.inventorymicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymicroservice.Entity.ProductHistory;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Long>{}
