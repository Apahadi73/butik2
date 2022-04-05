package com.butik.ProductService.query.event.handler;

import com.butik.ProductService.command.events.ProductCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */
public interface ProductEventHandler {
    public void on(ProductCreatedEvent productCreatedEvent);
}
