package com.customers.management.system.customersmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomersMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersMicroserviceApplication.class, args);
	}

}
