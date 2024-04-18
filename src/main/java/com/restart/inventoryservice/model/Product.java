package com.restart.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Integer productCount;
    private Double price;

    public static Product from(ProductDTO productDTO) {
        return Product.builder()
                .productCount(productDTO.getProductCount())
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .build();
    }
}
