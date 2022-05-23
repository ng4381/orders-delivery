package com.example.ordersdelivery.exception;

public class RouteDetailsIsNotEmptyException extends RuntimeException{
    public RouteDetailsIsNotEmptyException(String message) {
        super(message);
    }
}
