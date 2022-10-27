package com.inventorymicroservice.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymicroservice.Entity.ProductDetails;

@Repository
public interface InventoryRepository extends JpaRepository<ProductDetails,Long> {}
