package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.service.RouteService;
import com.example.ordersdelivery.service.TransportService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = TransportController.class)
class TransportControllerTest {

    @MockBean
    private RouteService routeService;

    @MockBean
    private TransportService transportService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTransports() throws Exception {

        Transport transport_1 = Transport.builder().type("Type1").number("123").build();
        Transport transport_2 = Transport.builder().type("Type2").number("456").build();

        Mockito.when(transportService.getTransports()).thenReturn(List.of(transport_1, transport_2));

        mockMvc.perform(MockMvcRequestBuilders.get("/routes/transports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", Matchers.is("123")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].number", Matchers.is("456")));
    }

    @Test
    void updateTransport() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/routes/transports/1/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}