package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.exception.RouteDeliveryPointsIsNotEmptyException;
import com.example.ordersdelivery.exception.RouteDetailsIsNotEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(value = RouteDetailsIsNotEmptyException.class)
    public ResponseEntity<RouteDetailsIsNotEmptyException> routeDetailsIsNotEmptyException() {
        log.info("RouteDetailsIsNotEmptyException Advice...");
        return new ResponseEntity("Route details is not empty", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RouteDeliveryPointsIsNotEmptyException.class)
    public ResponseEntity<RouteDeliveryPointsIsNotEmptyException> routeDeliveryPointsIsNotEmptyException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
