package com.butik.OrderService.query.handler.event;

import com.butik.OrderService.command.models.OrderCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */
public interface OrderEventsHandler {
    /**
     * brief: Handles instances of OrderCreatedEvent event
     * @param orderCreatedEvent - an instance of OrderCreatedEvent class
     */
    public void on(OrderCreatedEvent orderCreatedEvent) throws Exception;

    /**
     * brief: logs IllegalArgumentException. This method can only throw exceptions thrown by event handlers of the same class.
     * @param exception - an instance of IllegalArgumentException
     */
    public void handle(IllegalArgumentException exception);

    /**
     * brief: logs Exception. This method can only throw exceptions thrown by event handlers of the same class.
     * @param exception - an instance of Exception
     */
    public void handle(Exception exception);
}
