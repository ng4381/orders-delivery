package com.example.ordersdelivery.exception;

public class RouteNotFoundException extends RuntimeException{
    public RouteNotFoundException(String message) {
        super(message);
    }
}
