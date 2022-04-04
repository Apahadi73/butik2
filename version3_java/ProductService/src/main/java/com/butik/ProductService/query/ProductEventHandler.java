package com.butik.ProductService.query;

import com.butik.ProductService.core.events.ProductCreatedEvent.ProductCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */
public interface ProductEventHandler {
    public void on(ProductCreatedEvent productCreatedEvent);
}
