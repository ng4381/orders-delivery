package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.RouteDescDTO;
import com.example.ordersdelivery.entity.Route;
import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.exception.RouteNotFoundException;
import com.example.ordersdelivery.mapper.RouteDescMapper;
import com.example.ordersdelivery.repository.*;
import net.bytebuddy.pool.TypePool;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;
    @Mock
    private RouteDetailRepository routeDetailRepository;
    @Mock
    private RouteDeliveryPointRepository routeDeliveryPointRepository;
    @Mock
    private TransportService transportService;

    //@Mock
    RouteDescMapper routeDescMapper;

    @Captor
    private ArgumentCaptor<Route> routeArgumentCaptor;

    private RouteService routeService;



    @BeforeEach
    private void setup() {
        routeDescMapper = new RouteDescMapper();
        routeService = new RouteService(routeRepository, routeDetailRepository,
                routeDeliveryPointRepository, transportService, routeDescMapper);
    }

    @Test
    @DisplayName("Should return routes list")
    public void shouldReturnRouteList() {

        Mockito.when(routeRepository.findAll()).thenReturn(getRoutes());

        List<Route> expectedRoutes = routeService.getRoutesList();

        assertThat(expectedRoutes).usingRecursiveComparison().isEqualTo(getRoutes());
    }

    private List<Route> getRoutes() {
        LocalDate eta = LocalDate.of(2022, 1, 1);
        LocalDate etd = LocalDate.of(2022, 1, 1);

        Route route_1 = Route.builder()
                .id(1L)
                .transport(null)
                .from("from")
                .to("to")
                .ETA(eta)
                .ETD(etd)
                .build();

        Route route_2 = Route.builder()
                .id(2L)
                .transport(null)
                .from("from")
                .to("to")
                .ETA(eta)
                .ETD(etd)
                .build();

        return List.of(route_1, route_2);
    }

    private Transport getTransport() {
        return new Transport(1L, "123", "type", 1L, 1L, 1L, 80.);
    }

    @Test
    @DisplayName("Should create route")
    public void shouldCreateRoute() {
        Transport transport = getTransport();
        Mockito.when(transportService.getDefaultTransport()).thenReturn(transport);

        routeService.createRoute();
        Mockito.verify(routeRepository, Mockito.times(1)).save(routeArgumentCaptor.capture());

        Route capturedRoute = routeArgumentCaptor.getValue();

        assertThat(capturedRoute.getTransport()).usingRecursiveComparison().isEqualTo(transport);
    }

    private Route getRoute() {
        Transport transport = getTransport();
        return  Route.builder()
                .id(1L)
                .transport(transport)
                .from("from")
                .to("to")
                .ETA(null)
                .ETD(null)
                .build();
    }

    @Test
    @DisplayName("Should return route description")
    public void shouldReturnRouteDesc() {
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(getRoute()));
        RouteDescDTO expectedRouteDescDTO = routeService.getRouteDesc(1L);
        assertThat(expectedRouteDescDTO.getRouteId()).isEqualTo(getRoute().getId());
    }

    @Test
    @DisplayName("Should throw route not found exception")
    public void shouldThrowRouteNotFoundException() {
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.empty());
        RouteNotFoundException routeNotFoundException = assertThrows(RouteNotFoundException.class, () -> {
            routeService.getRouteDesc(1L);
        });
        assertThat(routeNotFoundException).isNotNull();
    }

    @Test
    @DisplayName("Should return route by id")
    public void shouldReturnRouteById() {
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(getRoute()));
        Route route = routeService.getRouteById(1L);
        assertThat(route).isNotNull();
    }

    @Test
    @DisplayName("Should throw route not found exception")
    public void shouldThrowRouteNotFoundExceptionWhileGettingRoutBuId() {
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.empty());
        RouteNotFoundException routeNotFoundException = assertThrows(RouteNotFoundException.class, () -> {
            routeService.getRouteById(1L);
        });
        assertThat(routeNotFoundException).isNotNull();
    }
}