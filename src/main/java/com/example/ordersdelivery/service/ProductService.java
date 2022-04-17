package com.example.ordersdelivery.service;

import com.example.ordersdelivery.dto.ProductDto;
import com.example.ordersdelivery.entity.Product;
import com.example.ordersdelivery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Product product) {

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()) {
            log.info("Product already exist: " + optionalProduct.toString());
            return optionalProduct.get();
        };

        Product _product = new Product();
        log.info("Creating new product: " + _product.toString());
        _product.setId(product.getId());
        _product.setName(product.getName());

        return productRepository.save(_product);
    }

    public Product getProduct(Long id, String name) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            log.info("Product already exist: " + optionalProduct.toString());
            return optionalProduct.get();
        };

        Product _product = new Product();
        log.info("Creating new product: " + _product.toString());
        _product.setId(id);
        _product.setName(name);

        return productRepository.save(_product);
    }

    public String getProductNameFromProductServiceById(Long id) {
        String url = "http://localhost:8076/products/get/{id}";
        ResponseEntity<ProductDto> response = null;
        try {
            response = this.restTemplate.getForEntity(url, ProductDto.class, id);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
            return "(product not found)";
        }

        return response.getBody().getName();



        /*
        ProductDto productDto = restTemplate.getForObject(
               "http://localhost:8076/products/get/{id}", ProductDto.class, id);

        if (productDto == null) {
            return "(not found)";
        }

        return productDto.getName();

         */
    }

}
