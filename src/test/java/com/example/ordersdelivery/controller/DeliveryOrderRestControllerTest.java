package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.dto.DeliveryOrderDetailDTOImpl;
import com.example.ordersdelivery.service.DeliveryOrderDetailService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = DeliveryOrderRestController.class)
class DeliveryOrderRestControllerTest {

    @MockBean
    private DeliveryOrderDetailService deliveryOrderDetailService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDeliveryOrderList() throws Exception {

        DeliveryOrderDetailDTOImpl deliveryOrderDetailDTO_1 = DeliveryOrderDetailDTOImpl.builder()
                .productId(1L)
                .productName("product-1")
                .qty(5)
                .deliveryOrderExtOrderId("ext order - 1")
                .deliveryOrderId(1L)
                .total(5)
                .build();

        DeliveryOrderDetailDTOImpl deliveryOrderDetailDTO_2 = DeliveryOrderDetailDTOImpl.builder()
                .productId(2L)
                .productName("product-2")
                .qty(3)
                .deliveryOrderExtOrderId("ext order - 1")
                .deliveryOrderId(1L)
                .total(3)
                .build();

        Mockito.when(deliveryOrderDetailService.getDeliveryOrderDetailDTO()).thenReturn(List.of(deliveryOrderDetailDTO_1, deliveryOrderDetailDTO_2));

        mockMvc.perform(MockMvcRequestBuilders.get("/delivery-orders-details"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName", Matchers.is("product-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].qty", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName", Matchers.is("product-2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].qty", Matchers.is(3)));


    }
}