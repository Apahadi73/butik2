package com.butik.ProductService.core.handler.error;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 */
public interface ProductServiceErrorHandler {
    /**
     * brief: handles illegal state exceptions
     * @param e - an instance of IllegalStateException
     * @param request - WebRequest instance
     * @return an instance of ResponseEntity with the error message and http status code
     */
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException e, WebRequest request);

    /**
     * brief: handles other exceptions
     * @param e - an instance of CommandExecutionException
     * @param request - WebRequest instance
     * @return an instance of ResponseEntity with the error message and http status code
     */
    public ResponseEntity<Object> handleCommandExecutionException(CommandExecutionException e, WebRequest request);

    /**
     * brief: handles other exceptions
     * @param e - an instance of Exception
     * @param request - WebRequest instance
     * @return an instance of ResponseEntity with the error message and http status code
     */
    public ResponseEntity<Object> handleOtherException(Exception e, WebRequest request);

}
