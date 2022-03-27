package com.hype.eventservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.hype.eventservice.api.util.MessageUtils.ERROR_MESSAGE;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception e){
        DefaultError error = new DefaultError(HttpStatus.BAD_GATEWAY.value(), ERROR_MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }
}
