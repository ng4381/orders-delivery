package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.DeliveryOrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final DeliveryOrderService deliveryOrderService;

    /*
    @KafkaListener(topics = "TestTopic", groupId = "group_id")
    public void consume(DeliveryOrderDTO deliveryOrderDTO) {
        log.info("kafka message = " + deliveryOrderDTO);
        deliveryOrderService.createDeliveryOrder(deliveryOrderDTO);
    }
     */

}
