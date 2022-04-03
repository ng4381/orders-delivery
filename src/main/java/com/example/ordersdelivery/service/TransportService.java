package com.example.ordersdelivery.service;

import com.example.ordersdelivery.entity.Transport;
import com.example.ordersdelivery.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransportService {
    private final TransportRepository transportRepository;

    public Transport getDefaultTransport() {
        Optional<Transport> optionalTransport = transportRepository.findById(1L);
        if(optionalTransport.isEmpty()) {
            log.warn("WARNING! Default transport with id = 1 was not found!");
            return null;
        }
        return optionalTransport.get();
    }
}
