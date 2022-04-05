package com.butik.ProductService.command.service;

import com.butik.ProductService.command.CreateProductCommand;
import com.butik.ProductService.core.models.CreateProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 31/03/2022
 */

@Service
public class ProductCreateServiceImpl implements ProductCreateService {

    // field variables
    private CommandGateway commandGateway;

    @Autowired
    public ProductCreateServiceImpl(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    /**
     * brief: creates a new CreateProductRestModel object
     *
     * @param createProductRestModel - a CreateProductRestModel object
     * @return string representation of newly CreateProductRestModel object
     */
    public String createProduct(CreateProductRestModel createProductRestModel) throws Exception {
        // create a new CreateProductCommand
        CreateProductCommand createProductCommand= CreateProductCommand.builder()
                .title(createProductRestModel.getTitle())
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        // send the commands
        String response;
        response = commandGateway.sendAndWait(createProductCommand);
        return response;
    }
}
