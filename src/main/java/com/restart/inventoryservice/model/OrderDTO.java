package com.restart.inventoryservice.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> items;
    private OrderStatus status;
}
