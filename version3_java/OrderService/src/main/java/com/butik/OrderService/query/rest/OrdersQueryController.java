package com.butik.OrderService.query.rest;

import com.butik.OrderService.query.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
@RestController
@RequestMapping("/orders")
public class OrdersQueryController {

    // field variables
    private final Environment environment;
    private final OrderQueryService orderQueryService;

    @Autowired
    public OrdersQueryController(Environment environment, OrderQueryService orderQueryService){
        this.environment = environment;
        this.orderQueryService = orderQueryService;
    }
    
    @GetMapping("/version")
    public String getCurrentVersion(){
        return "Version of service: " + environment.getProperty("service.version");
    }

}
