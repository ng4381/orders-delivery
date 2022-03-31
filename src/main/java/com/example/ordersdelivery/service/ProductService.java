package com.example.ordersdelivery.service;

import com.example.ordersdelivery.entity.Product;
import com.example.ordersdelivery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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

}
