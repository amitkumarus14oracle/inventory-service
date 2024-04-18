package com.restart.inventoryservice.controller;

import com.restart.inventoryservice.model.Product;
import com.restart.inventoryservice.model.ProductDTO;
import com.restart.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = Product.from(productDTO);
        product = productService.addProduct(product);
        return new ResponseEntity<>(ProductDTO.from(product), HttpStatus.CREATED);
    }
}
