package com.butik.OrderService.command.aggregate;

import com.butik.OrderService.command.models.CreateOrderCommand;
import com.butik.OrderService.command.models.OrderCreatedEvent;
import com.butik.OrderService.command.models.OrderStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */
@Aggregate
@NoArgsConstructor
@Slf4j
public class OrderAggregateImpl implements OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    @CommandHandler
    public OrderAggregateImpl(CreateOrderCommand createProductCommand){
        // handle OrderCreatedEvent
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,orderCreatedEvent);

        // stage productCreatedEvent for execution
        AggregateLifecycle.apply(orderCreatedEvent);
        log.info("OrderCreatedEvent", "create order event received by CommandHandler");
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent ){
        this.orderId = orderCreatedEvent.getOrderId();
        this.productId = orderCreatedEvent.getProductId();
        this.userId = orderCreatedEvent.getUserId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.addressId = orderCreatedEvent.getAddressId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
        log.info("OrderCreatedEvent", "create order event received by EventSourcingHandler");
    }
}
