package com.butik.ProductService.command.interceptor;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
public interface CreateProductCommandInterceptor extends MessageDispatchInterceptor<CommandMessage<?>> {
}
