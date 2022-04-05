package com.butik.OrderService.command.service;

import com.butik.OrderService.command.models.CreateOrderRestModel;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
public interface OrderCommandService {
    /**
     * brief: create a new order
     * @param createOrderRestModel
     * @return returns an order id
     * @throws Exception
     */
    String createOrder(CreateOrderRestModel createOrderRestModel) throws Exception;
}
