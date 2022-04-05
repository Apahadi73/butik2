package com.butik.OrderService.query.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */

@Service
@Slf4j
public class OrderQueryServiceImpl implements OrderQueryService {

    // field variables
    private final QueryGateway queryGateway;

    @Autowired
    public OrderQueryServiceImpl(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

}
