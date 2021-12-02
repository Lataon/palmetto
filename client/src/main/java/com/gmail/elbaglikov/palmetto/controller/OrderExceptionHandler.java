package com.gmail.elbaglikov.palmetto.controller;

import com.gmail.elbaglikov.palmetto.exception.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String employeeNotFoundHandler(OrderNotFoundException ex) {
        LOGGER.warn("Returning HTTP 400 Bad Request", ex);
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception ex) {
        LOGGER.warn("Returning HTTP 400 Bad Request", ex);
        return ex.getMessage();
    }
}
