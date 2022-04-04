package com.butik.ProductService.core.service;

import com.butik.ProductService.command.CreateProductCommand;
import com.butik.ProductService.core.entity.CreateProductRestModel;
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
public class ProductServiceImpl implements ProductService {

    // field variables
    private CommandGateway commandGateway;

    @Autowired
    public ProductServiceImpl(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    /**
     * brief: creates a new CreateProductRestModel object
     *
     * @param createProductRestModel - a CreateProductRestModel object
     * @return string representation of newly CreateProductRestModel object
     */
    public String createProduct(CreateProductRestModel createProductRestModel) {
        // create a new CreateProductCommand
        CreateProductCommand createProductCommand= CreateProductCommand.builder()
                .title(createProductRestModel.getTitle())
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        // send the commands
        String response;
        try {
            response = commandGateway.sendAndWait(createProductCommand);
        }
        catch (Exception e){
            response = e.getLocalizedMessage();
        }

        return response;
    }
}
