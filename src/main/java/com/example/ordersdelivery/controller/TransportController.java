package com.example.ordersdelivery.controller;

import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.service.RouteService;
import com.example.ordersdelivery.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransportController {

    private final TransportService transportService;
    private final RouteService routeService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/routes/transports")
    public List<Transport> getTransports() {
        return transportService.getTransports();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/routes/transports/{id}/{routeId}")
    public ResponseEntity<HttpStatus> updateTransport(@PathVariable Long id, @PathVariable Long routeId) {
        routeService.updateTransport(id, routeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
