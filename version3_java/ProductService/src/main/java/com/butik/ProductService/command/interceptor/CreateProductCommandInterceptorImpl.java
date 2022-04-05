package com.butik.ProductService.command.interceptor;

import com.butik.ProductService.command.CreateProductCommand;
import com.butik.ProductService.core.models.ProductLookUpEntity;
import com.butik.ProductService.core.repository.ProductLookupRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 * brief: Handles all the interception logics related to CreateProductCommand command
 */
@Component
@Slf4j
public class CreateProductCommandInterceptorImpl implements CreateProductCommandInterceptor {

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptorImpl(ProductLookupRepository productLookupRepository){
        this.productLookupRepository = productLookupRepository;
    }


    /**
     * Invoked each time a message is about to be dispatched. The given {@code message} represents the message
     * being dispatched.
     *
     * @param message The message intended to be dispatched
     * @return the message to dispatch
     */
    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        return CreateProductCommandInterceptor.super.handle(message);
    }

    /**
     * Apply this interceptor to the given list of {@code messages}. This method returns a function that can be
     * invoked to obtain a modified version of messages at each position in the list.
     *
     * @param messages The Messages to pre-process
     * @return a function that processes messages based on their position in the list
     */
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index,command) -> {
            log.info("Intercepted commands",command.getPayload());
            if (CreateProductCommand.class.equals(command.getPayloadType())){
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                // check whether the product already exists or not
                ProductLookUpEntity productLookUpEntity =productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
                if (productLookUpEntity != null){
                    throw new IllegalStateException(
                            String.format("Product with productId %s or title %s already exists",
                                    createProductCommand.getProductId(), createProductCommand.getTitle()
                            )
                    );
                }
            }
            return command;
        };
    }
}
