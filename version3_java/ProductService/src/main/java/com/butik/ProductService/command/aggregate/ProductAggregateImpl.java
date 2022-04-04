package com.butik.ProductService.command.aggregate;

import com.butik.ProductService.command.CreateProductCommand;
import com.butik.ProductService.core.events.ProductCreatedEvent.ProductCreatedEvent;
import lombok.NoArgsConstructor;
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
public class ProductAggregateImpl implements ProductAggregate{

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregateImpl(CreateProductCommand createProductCommand){

        // Validate Create Product Command
        if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price of the product cannot be less or equal than zero.");
        }

        if(createProductCommand.getTitle() == null){
            throw new IllegalArgumentException("Product must have an valid non-empty title.");
        }

        // handle ProductCreatedEvent
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
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
    }
}
