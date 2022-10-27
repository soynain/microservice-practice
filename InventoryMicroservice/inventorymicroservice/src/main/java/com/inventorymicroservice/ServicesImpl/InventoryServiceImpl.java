package com.inventorymicroservice.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorymicroservice.Entity.ProductDetails;
import com.inventorymicroservice.Repository.InventoryRepository;
import com.inventorymicroservice.Services.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public ProductDetails addProduct(ProductDetails newProduct) {
        return inventoryRepository.save(newProduct);
    }

    @Override
    public void removeProduct(Long id) {
        inventoryRepository.deleteById(id); 
    }

    @Override
    public ProductDetails modifyProduct(ProductDetails productNewData, Long idOfProductToModify) {
        ProductDetails productToBeModified=inventoryRepository.findById(idOfProductToModify).get();
        productToBeModified.setProduct_name(productNewData.getProduct_name());
        productToBeModified.setProduct_type(productNewData.getProduct_type());
        productToBeModified.setProduct_description(productNewData.getProduct_description());
        productToBeModified.setPrice_per_unit(productNewData.getPrice_per_unit());
        return inventoryRepository.save(productToBeModified);
    }

}
