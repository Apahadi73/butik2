package com.butik.ProductService.command.handler.event;

import com.butik.ProductService.core.models.ProductCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
public interface ProductLookUpEventHandler {
    /**
     * brief: extracts title and productId from productCreatedEvent, creates a new instance of  ProductLookUpEntity, and saves it in the datastore
     * @param productCreatedEvent - an instance of ProductCreatedEvent
     */
    public void on(ProductCreatedEvent productCreatedEvent);
}
