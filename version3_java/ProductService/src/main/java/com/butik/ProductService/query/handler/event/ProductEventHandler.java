package com.butik.ProductService.query.handler.event;

import com.butik.ProductService.core.models.ProductCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */
public interface ProductEventHandler {
    /**
     * brief: Handles instances of ProductCreatedEvent event
     * @param productCreatedEvent - an instance of ProductCreatedEvent class
     */
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception;

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
