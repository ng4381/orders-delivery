package com.example.ordersdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/web/home")
    public String home() {
        return "/home/view";
    }
}
