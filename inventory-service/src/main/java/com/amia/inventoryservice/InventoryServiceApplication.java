package com.amia.inventoryservice;

import com.amia.inventoryservice.entities.Product;
import com.amia.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("P1").price(111111).quantity(111111).build());
            productRepository.save(Product.builder().name("P2").price(22222).quantity(22222).build());
            productRepository.save(Product.builder().name("P3").price(33333).quantity(33333).build());
        };
    }

}
