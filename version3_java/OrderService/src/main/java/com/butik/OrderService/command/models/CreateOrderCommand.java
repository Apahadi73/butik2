package com.butik.OrderService.command.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Data
@Builder
public class CreateOrderCommand {
    private final String orderId;
    private final String userId;
    private final String productId;
    private final String addressId;
    private final int quantity;
    private final OrderStatus orderStatus;
}


