package com.butik.OrderService.command.aggregate;

import com.butik.OrderService.command.models.OrderCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
public interface OrderAggregate {
    /**
     * brief: handle OrderCreatedEvent event
     * @param orderCreatedEvent
     */
    public void on(OrderCreatedEvent orderCreatedEvent);
}
