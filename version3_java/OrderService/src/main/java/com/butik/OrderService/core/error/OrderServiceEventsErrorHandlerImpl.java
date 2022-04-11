package com.butik.ProductService.core.handler.error;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.springframework.stereotype.Component;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 * brief: handles all operations related to ListenerInvocationErrorHandler
 */
@Component
public class ProductServiceEventsErrorHandlerImpl implements  ProductServiceEventsErrorHandler {
    /**
     * @param exception    The exception thrown by the given eventListener
     * @param event        The event that triggered the exception
     * @param eventHandler The listener that failed to handle given event
     * @throws Exception To stop further handling of the event
     */
    @Override
    public void onError(Exception exception, EventMessage<?> event, EventMessageHandler eventHandler) throws Exception {
        throw exception;
    }
}
