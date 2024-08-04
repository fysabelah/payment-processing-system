package com.payment.system.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.payment.system.customer_service.frameworks.web")
public class CustomersMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersMicroserviceApplication.class, args);
    }

}
