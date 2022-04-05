package com.butik.ProductService.command.handler.event;

import com.butik.ProductService.core.models.ProductCreatedEvent;
import com.butik.ProductService.core.models.ProductLookUpEntity;
import com.butik.ProductService.core.repository.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@Component
//Logically groups event handler together
@ProcessingGroup("product-group")
public class ProductLookUpEventHandlerImpl implements ProductLookUpEventHandler {
    private final ProductLookupRepository productLookupRepository;

    public ProductLookUpEventHandlerImpl(ProductLookupRepository productLookupRepository){
        this.productLookupRepository = productLookupRepository;
    }

    /**
     * brief: extracts title and productId from productCreatedEvent, creates a new instance of  ProductLookUpEntity, and saves it in the datastore
     * @param productCreatedEvent - an instance of ProductCreatedEvent
     */
    @Override
    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookUpEntity productLookUpEntity = new ProductLookUpEntity(productCreatedEvent.getProductId(),
                productCreatedEvent.getTitle());
        productLookupRepository.save(productLookUpEntity);
    }
}
