package com.butik.ProductService.query.handler.event;

import com.butik.ProductService.core.models.ProductCreatedEvent;
import com.butik.ProductService.core.models.ProductEntity;
import com.butik.ProductService.core.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    /**
     * brief: Handles instances of ProductCreatedEvent event
     * @param productCreatedEvent - an instance of ProductCreatedEvent class
     */
    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        log.info("ProductCreatedEvent", "create product event intercepted by event handler");
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);
        productRepository.save(productEntity);
        if (true){
            throw new Exception("Forcing exception in the Product Event Handler class");
        }
        log.info("ProductCreatedEvent", "new product saved into the database");
    }

    /**
     * brief: handlers IllegalArgumentException
     * @param exception - an instance of IllegalArgumentException class
     */
    @Override
    @ExceptionHandler(value={IllegalArgumentException.class})
    public void handle(IllegalArgumentException exception) {

    }

    /**
     * brief: logs Exception. This method can only throw exceptions thrown by event handlers of the same class.
     * @param exception - an instance of Exception
     */
    @Override
    @ExceptionHandler(value={Exception.class})
    public void handle(Exception exception) {

    }
}
