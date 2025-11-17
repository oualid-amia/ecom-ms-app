package com.amia.customerservice;

import com.amia.customerservice.entities.Customer;
import com.amia.customerservice.repositoty.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().name("moh").email("moh@gmail.com").build());
            customerRepository.save(Customer.builder().name("moh1").email("moh1@gmail.com").build());
            customerRepository.save(Customer.builder().name("moh2").email("moh2@gmail.com").build());
        };
    }

}
