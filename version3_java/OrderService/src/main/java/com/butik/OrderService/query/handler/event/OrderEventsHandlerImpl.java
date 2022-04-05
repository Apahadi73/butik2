package com.butik.OrderService.query.handler.event;

import com.butik.OrderService.command.models.OrderCreatedEvent;
import com.butik.OrderService.core.models.OrderEntity;
import com.butik.OrderService.core.repository.OrdersRepository;
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
public class OrderEventsHandlerImpl implements OrderEventsHandler {
    private final OrdersRepository ordersRepository;

    public OrderEventsHandlerImpl(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    /**
     * brief: Handles instances of OrderCreatedEvent event
     * @param orderCreatedEvent - an instance of OrderCreatedEvent class
     */
    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) throws Exception {
        log.info("OrderCreatedEvent", "create order event intercepted by event handler");
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, orderEntity);
        // save in the database
        ordersRepository.save(orderEntity);
        log.info("OrderCreatedEvent", "new order saved into the database");
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
