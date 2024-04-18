package com.restart.inventoryservice.service;

import com.restart.inventoryservice.exception.InsufficientProductCountException;
import com.restart.inventoryservice.model.OrderDTO;
import com.restart.inventoryservice.model.OrderItemDTO;
import com.restart.inventoryservice.model.OrderStatus;
import com.restart.inventoryservice.model.Product;
import com.restart.inventoryservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    private final Logger logger = LoggerFactory.getLogger(InventoryService.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    KafkaTemplate<String, OrderDTO> template;

    @KafkaListener(topics = "order-created", groupId = "order")
    @Transactional
    public void handleOrderCreated(@Payload OrderDTO order) {
        // Update the inventory levels based on the order details
        for (final OrderItemDTO item : order.getItems()) {
            logger.info(String.format("$$ -> Consumed Message -> %s",item));
            try {
                Product product = productService.getProductById(item.getProductId());
                if(product.getProductCount() - item.getQuantity() < 0) {
                    throw new InsufficientProductCountException("Existing product count is less than ordered product count");
                }
                product.setProductCount(product.getProductCount() - item.getQuantity());
                productRepository.save(product);
                /*product.ifPresent(product1 ->  {
                    product1.setProductCount(product1.getProductCount() - item.getQuantity());
                    repository.save(product1);
                });*/
            } catch (Exception ex) {
                order.setStatus(OrderStatus.FAILED);
                template.send("order-reversed", order);
                return;
            }
        }
    }
}