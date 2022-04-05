package com.butik.ProductService.query.handler.event;

import com.butik.ProductService.core.models.ProductEntity;
import com.butik.ProductService.command.events.ProductCreatedEvent;
import com.butik.ProductService.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 04/04/2022
 */

@Component
@Slf4j
//Logically groups event handlers together
@ProcessingGroup("product-group")
public class ProductEventHandlerImpl implements ProductEventHandler{
    private final ProductRepository productRepository;

    public ProductEventHandlerImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        log.info("ProductCreatedEvent", "create product event intercepted by event handler");
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);
        productRepository.save(productEntity);
        log.info("ProductCreatedEvent", "new product saved into the database");
    }
}
