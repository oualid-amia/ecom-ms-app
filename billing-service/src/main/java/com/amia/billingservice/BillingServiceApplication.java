package com.amia.billingservice;

import com.amia.billingservice.entities.Bill;
import com.amia.billingservice.entities.ProductItem;
import com.amia.billingservice.repository.BillRepository;
import com.amia.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemRepository productItemRepository) {
        return args -> {
            List<Long> customersIds = List.of(1L, 2L, 3L);
            List<Long> productIds = List.of(1L, 2L, 3L);
            customersIds.forEach(customerId -> {
                Bill bill = new Bill();
                bill.setBillDate(new Date());
                bill.setCustomerId(customerId);
                billRepository.save(bill);

                productIds.forEach(productId -> {
                    ProductItem productItem = new ProductItem();
                    productItem.setPrice(1000*Math.random()*600);
                    productItem.setQuantity(1+new Random().nextInt(20));
                    productItem.setProductId(productId);
                    productItem.setBill(bill);
                    productItemRepository.save(productItem);
                });
            });
        };
    }

}
