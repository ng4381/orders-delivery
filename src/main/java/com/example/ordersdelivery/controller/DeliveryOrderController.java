package com.example.ordersdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeliveryOrderController {

    @GetMapping("/web/delivery-orders")
    public String deliveryOrderList() {
        return "deliveryorder/list";
    }

}
