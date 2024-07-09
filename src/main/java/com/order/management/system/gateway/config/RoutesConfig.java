package com.order.management.system.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    @Value("${customer.service.address}")
    private String customerUri;

    @Value("${order.service.address}")
    private String orderUri;

    @Value("${logistics.service.address}")
    private String logisticsUri;

    @Value("${inventory.service.address}")
    private String inventoryUri;

    @Bean
    public RouteLocator custom(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("customers-microservice", r -> r.path("/order-management-system/customers-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(customerUri))
                .route("ordering-microservice", r -> r.path("/order-management-system/ordering-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(orderUri))
                .route("product-microservice", r -> r.path("/order-management-system/product-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(inventoryUri))
                .route("tracking-microservice", r -> r.path("/order-management-system/tracking-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(logisticsUri))
                .build();
    }
}
