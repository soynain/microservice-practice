package com.inventorymicroservice.Services;

import com.inventorymicroservice.Entity.ProductDetails;

public interface InventoryService {
    ProductDetails addProduct(ProductDetails newProduct);
    void removeProduct(Long id);
    ProductDetails modifyProduct(ProductDetails productNewData,Long idOfProductToModify);
}
