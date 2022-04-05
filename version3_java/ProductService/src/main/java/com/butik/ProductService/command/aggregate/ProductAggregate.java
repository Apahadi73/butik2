package com.butik.ProductService.command.aggregate;

import com.butik.ProductService.command.events.ProductCreatedEvent;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
public interface ProductAggregate {
    public void on(ProductCreatedEvent productCreatedEvent);
}
