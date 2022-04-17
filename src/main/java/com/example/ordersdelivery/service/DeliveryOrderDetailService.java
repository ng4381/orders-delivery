package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.DeliveryOrderDetailDTO;
import com.example.ordersdelivery.dto.DeliveryOrderDetailDTOImpl;
import com.example.ordersdelivery.entity.DeliveryOrderDetail;
import com.example.ordersdelivery.repository.DeliveryOrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryOrderDetailService {

    private final DeliveryOrderDetailRepository deliveryOrderDetailRepository;
    private final ProductService productService;

    public void save(DeliveryOrderDetail deliveryOrderDetail) {
        deliveryOrderDetailRepository.save(deliveryOrderDetail);
    }

    public List<DeliveryOrderDetailDTOImpl> getDeliveryOrderDetailDTO() {
        List<DeliveryOrderDetailDTOImpl> deliveryOrderDetails = deliveryOrderDetailRepository.getDeliveryOrderDetailDTO().stream()
                .map(deliveryOrderDetailDTO -> new DeliveryOrderDetailDTOImpl(deliveryOrderDetailDTO))
                .collect(Collectors.toList());

        for (DeliveryOrderDetailDTOImpl dod : deliveryOrderDetails) {
            dod.setProductName(productService.getProductNameFromProductServiceById(dod.getProductId()));

        }
        return deliveryOrderDetails;
    }

    public DeliveryOrderDetail getDeliveryOrderDetailById(Long id) {
        return deliveryOrderDetailRepository.getById(id);
    }
}
