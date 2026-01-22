package com.ecommerce.bootloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.ecommerce"})
@EnableJpaRepositories(basePackages = {"com.ecommerce"})
@EntityScan(basePackages = {"com.ecommerce"})
@EnableTransactionManagement
public class EcommerceSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceSpringBootApplication.class, args);
    }
    
}