package com.butik.OrderService.command.rest;

import com.butik.OrderService.command.models.CreateOrderRestModel;
import com.butik.OrderService.command.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 * brief: handles all the command requests of Order Service
 */
@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    private OrderCommandService orderCommandService;

    @Autowired
    public OrdersCommandController(OrderCommandService orderCommandService){
        this.orderCommandService = orderCommandService;
    }

    @PostMapping
    public String createOrder(@Valid @RequestBody CreateOrderRestModel createProductRestModel) throws Exception {
        return orderCommandService.createOrder(createProductRestModel);
    }
}
