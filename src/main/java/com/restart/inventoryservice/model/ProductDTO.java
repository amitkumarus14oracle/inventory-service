package com.restart.inventoryservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Integer productCount;
    private String productName;
    private Double price;

    public static ProductDTO from(Product product) {
        return ProductDTO.builder()
                .productCount(product.getProductCount())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
}
