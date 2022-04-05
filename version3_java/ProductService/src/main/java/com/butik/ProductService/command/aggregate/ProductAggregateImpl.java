package com.butik.ProductService.command.aggregate;

import com.butik.ProductService.command.CreateProductCommand;
import com.butik.ProductService.core.models.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
@Aggregate
@NoArgsConstructor
@Slf4j
public class ProductAggregateImpl implements ProductAggregate{

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregateImpl(CreateProductCommand createProductCommand){
        // validate Create Product Command
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price cannot be less or equal to zero");
        }

        if (createProductCommand.getTitle() == null ||createProductCommand.getTitle() .isBlank() ){
            throw new IllegalArgumentException("Title cannot be empty");
        }

        // handle ProductCreatedEvent
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);

        // stage productCreatedEvent for execution
        AggregateLifecycle.apply(productCreatedEvent);
        log.info("CreateProductEvent", "create product event received by CommandHandler");
    }

    /**
     * brief: updates the aggregate state
     * @param productCreatedEvent - a custom product created event
     */
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
        log.info("CreateProductEvent", "create product event received by EventSourcingHandler");
    }
}
