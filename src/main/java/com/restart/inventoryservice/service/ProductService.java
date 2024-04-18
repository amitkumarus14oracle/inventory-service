package com.restart.inventoryservice.service;

import com.restart.inventoryservice.exception.ProductNotFoundException;
import com.restart.inventoryservice.model.Product;
import com.restart.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long productId) {
        try{
            Optional<Product> product = productRepository.findById(productId);
            if(product.isPresent()) {
                return product.get();
            } else {
                throw new ProductNotFoundException("Product with given id "+ product +"does not exist in system");
            }
        }catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
