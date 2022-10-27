package com.inventorymicroservice.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inventorymicroservice.Entity.ProductDetails;
import com.inventorymicroservice.ServicesImpl.InventoryServiceImpl;

@RestController
@RequestMapping("/inventory/edit")
public class InventoryController {
    @Autowired
    InventoryServiceImpl inventoryServiceImpl;


    @PostMapping("/add")
    public ProductDetails addProduct(@RequestBody ProductDetails newProduct){
        return inventoryServiceImpl.addProduct(newProduct);
    }

    @RequestMapping(value = "/remove",method = RequestMethod.POST,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeProduct(@RequestBody Long id) throws JsonProcessingException{
        inventoryServiceImpl.removeProduct(id);
        ObjectMapper mapper=new ObjectMapper();
        ObjectNode responseBody=mapper.createObjectNode();
        responseBody.put("Status", ResponseEntity.ok().toString());
        responseBody.put(null, "Product has been deleted successfully");
        return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseBody), HttpStatus.OK);
    }

    @PostMapping("/modify")
    public ProductDetails modifyProduct(@RequestBody ProductDetails productNewData,Long id){
        return inventoryServiceImpl.modifyProduct(productNewData, id);
    }
}
