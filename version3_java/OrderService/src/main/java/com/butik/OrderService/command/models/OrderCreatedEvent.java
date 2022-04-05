package com.butik.OrderService.command.models;

import lombok.Data;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Data
public class OrderCreatedEvent {
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
