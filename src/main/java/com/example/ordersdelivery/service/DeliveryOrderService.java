package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.DeliveryOrderDTO;
import com.example.ordersdelivery.entity.DeliveryOrder;
import com.example.ordersdelivery.entity.DeliveryOrderDetail;
import com.example.ordersdelivery.entity.Product;
import com.example.ordersdelivery.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryOrderDetailService deliveryOrderDetailService;
    private final ProductService productService;

    public DeliveryOrder getDeliveryOrderById(Long id) {
        return deliveryOrderRepository.getById(id);
    }

    public void createDeliveryOrder(DeliveryOrderDTO deliveryOrderDTO) {
        //TODO
        //Check if order with that ext order exist ... better to check by AssemblyOrder id

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setExtOrderId(deliveryOrderDTO.getExtOrderId());
        deliveryOrder = deliveryOrderRepository.save(deliveryOrder);
        log.info("Delivery order " + deliveryOrder.getId() + " created");

        for(DeliveryOrderDTO.DeliveryOrderDetailDTO dod : deliveryOrderDTO.getDeliveryOrderDetails()) {



            log.info("Creating delivery order detail ...");
            DeliveryOrderDetail deliveryOrderDetail = new DeliveryOrderDetail();
            deliveryOrderDetail.setDeliveryOrder(deliveryOrder);
            deliveryOrderDetail.setQty(dod.getQty());

            //Product product = productService.getProduct(dod.getProduct());
            //deliveryOrderDetail.setProduct( product );

            deliveryOrderDetail.setProduct_id(dod.getProduct().getId());

            deliveryOrderDetailService.save(deliveryOrderDetail);
            log.info("Delivery order detail created");
        }
    }

    public void save(DeliveryOrder deliveryOrder) {
        deliveryOrderRepository.save(deliveryOrder);
    }

}
