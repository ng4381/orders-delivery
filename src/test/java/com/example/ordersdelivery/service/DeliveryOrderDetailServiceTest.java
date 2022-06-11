package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.DeliveryOrderDetailDTO;
import com.example.ordersdelivery.dto.DeliveryOrderDetailDTOImpl;
import com.example.ordersdelivery.entity.DeliveryOrderDetail;
import com.example.ordersdelivery.repository.DeliveryOrderDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeliveryOrderDetailServiceTest {

    @Mock
    private DeliveryOrderDetailRepository deliveryOrderDetailRepository;
    @Mock
    private ProductService productService;

    @Captor
    private ArgumentCaptor<DeliveryOrderDetail> argumentCaptor;

    private DeliveryOrderDetailService deliveryOrderDetailService;

    @BeforeEach
    public void setup() {
        deliveryOrderDetailService = new DeliveryOrderDetailService(deliveryOrderDetailRepository, productService);
    }

    @Test
    public void shouldReturnDeliveryOrderDetailDTO() {

        DeliveryOrderDetailDTO deliveryOrderDetail_1 = DeliveryOrderDetailDTOImpl
                .builder()
                .deliveryOrderId(1L)
                .productId(1L)
                .productName("")
                .build();

        DeliveryOrderDetailDTO deliveryOrderDetail_2 = DeliveryOrderDetailDTOImpl
                .builder()
                .deliveryOrderId(2L)
                .productId(2L)
                .productName("")
                .build();


        Mockito.when(deliveryOrderDetailRepository.getDeliveryOrderDetailDTO()).thenReturn(List.of(deliveryOrderDetail_1, deliveryOrderDetail_2));
        Mockito.when(productService.getProductNameFromProductServiceById(1L)).thenReturn("product-1");
        Mockito.when(productService.getProductNameFromProductServiceById(2L)).thenReturn("product-2");

        List<DeliveryOrderDetailDTOImpl> expectedDeliveryOrderDetailDTOS =  deliveryOrderDetailService.getDeliveryOrderDetailDTO();

        Mockito.verify(deliveryOrderDetailRepository, Mockito.times(1)).getDeliveryOrderDetailDTO();
        Mockito.verify(productService, Mockito.times(1)).getProductNameFromProductServiceById(1L);
        Mockito.verify(productService, Mockito.times(1)).getProductNameFromProductServiceById(2L);

        assertThat(expectedDeliveryOrderDetailDTOS.stream()
                .map(DeliveryOrderDetailDTOImpl::getProductName)
                .collect(Collectors.toList()))
                .isEqualTo(List.of("product-1","product-2"));

    }

    @Test
    public void shouldSaveDeliveryOrderDetail() {
        DeliveryOrderDetail deliveryOrderDetail = DeliveryOrderDetail.builder()
                .id(1L)
                .deliveryOrder(null)
                .product_id(1L)
                .qty(5)
                .build();

        deliveryOrderDetailService.save(deliveryOrderDetail);

        Mockito.verify(deliveryOrderDetailRepository).save(argumentCaptor.capture());

        DeliveryOrderDetail capturedDeliveryOrderDetail = argumentCaptor.getValue();
        assertThat(capturedDeliveryOrderDetail).isNotNull();
        assertThat(capturedDeliveryOrderDetail.getId()).isEqualTo(1L);
    }
}