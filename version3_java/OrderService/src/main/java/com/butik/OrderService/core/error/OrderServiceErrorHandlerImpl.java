package com.butik.ProductService.core.handler.error;

import com.butik.ProductService.core.models.ErrorMessage;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * @author amirpahadi
 * @version 1.0
 * @since 05/04/2022
 * brief: handles error or exceptions for our product service
 */
@ControllerAdvice
public class ProductServiceErrorHandlerImpl implements ProductServiceErrorHandler{
    /**
     * @param e       - an instance of IllegalStateException
     * @param request - WebRequest instance
     * @return an instance of ResponseEntity with the error message and http status code
     */
    @Override
    @ExceptionHandler(value={IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * brief: handles other exceptions
     *
     * @param e       - an instance of CommandExecutionException
     * @param request - WebRequest instance
     * @return an instance of ResponseEntity with the error message and http status code
     */
    @Override
    @ExceptionHandler(value={CommandExecutionException.class})
    public ResponseEntity<Object> handleCommandExecutionException(CommandExecutionException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * brief: handles other exceptions
     * @param e       - an instance of Exception
     * @param request - WebRequest instance
     * @return an instance of ResponseEntity with the error message and http status code
     */
    @Override
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
