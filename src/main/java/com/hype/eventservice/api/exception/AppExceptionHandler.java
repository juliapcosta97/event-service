package com.hype.eventservice.api.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


import static com.hype.eventservice.api.util.MessageUtils.ERROR_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.NOT_FOUND_ERROR_MESSAGE;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> badGatewayHandlerException(Exception e){
        logger.error(ERROR_MESSAGE,e);
        DefaultError error = new DefaultError(HttpStatus.BAD_GATEWAY.value(), ERROR_MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler({NoSuchElementException.class, NullPointerException.class})
    public ResponseEntity<?> notFoundHandlerException(Exception e){
        logger.error(ERROR_MESSAGE,e);
        DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), NOT_FOUND_ERROR_MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
