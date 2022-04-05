package com.butik.ProductService.query.event.handler;

import com.butik.ProductService.core.models.ProductEntity;
import com.butik.ProductService.command.events.ProductCreatedEvent;
import com.butik.ProductService.core.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */

@Component
public class ProductEventHandlerImpl implements ProductEventHandler{
    private final ProductRepository productRepository;

    public ProductEventHandlerImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);
        productRepository.save(productEntity);
    }
}
