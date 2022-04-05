package com.butik.OrderService.command.service;

import com.butik.OrderService.command.models.CreateOrderCommand;
import com.butik.OrderService.command.models.CreateOrderRestModel;
import com.butik.OrderService.command.models.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */

@Service
@Slf4j
public class OrderCommandServiceImpl implements OrderCommandService {

    // field variables
    private final CommandGateway commandGateway;

    @Autowired
    public OrderCommandServiceImpl(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    /**
     * brief: create a new order
     * @param createOrderRestModel
     * @return return response of CreateOrderCommand command
     * @throws Exception
     */
    @Override
    public String createOrder(CreateOrderRestModel createOrderRestModel) throws Exception {
        // create a new CreateOrderCommand
        CreateOrderCommand createProductCommand= CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(createOrderRestModel.getProductId())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .quantity(createOrderRestModel.getQuantity())
                .addressId(createOrderRestModel.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .build();

        // send the commands
        String response;
        response = commandGateway.sendAndWait(createProductCommand);
        return response;
    }
}
