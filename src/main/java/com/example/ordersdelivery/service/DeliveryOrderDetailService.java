package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.DeliveryOrderDetailDTO;
import com.example.ordersdelivery.entity.DeliveryOrderDetail;
import com.example.ordersdelivery.repository.DeliveryOrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryOrderDetailService {

    private final DeliveryOrderDetailRepository deliveryOrderDetailRepository;

    public void save(DeliveryOrderDetail deliveryOrderDetail) {
        deliveryOrderDetailRepository.save(deliveryOrderDetail);
    }

    public List<DeliveryOrderDetailDTO> getDeliveryOrderDetailDTO() {
        return deliveryOrderDetailRepository.getDeliveryOrderDetailDTO();
    }

}
