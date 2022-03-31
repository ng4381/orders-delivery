package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.dto.DeliveryOrderDetailDTO;
import com.example.ordersdelivery.service.DeliveryOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryOrderRestController {

    private final DeliveryOrderDetailService deliveryOrderDetailService;

    @GetMapping("/delivery-orders-details")
    public List<DeliveryOrderDetailDTO> getDeliveryOrderList() {
        return deliveryOrderDetailService.getDeliveryOrderDetailDTO();
    }

}
