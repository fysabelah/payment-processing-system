package com.order.management.system.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator custom(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("customers-microservice", r -> r.path("/order-management-system/customers-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://customers-microservice"))
                .route("ordering-microservice", r -> r.path("/order-management-system/ordering-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://ordering-microservice"))
                .route("product-microservice", r -> r.path("/order-management-system/product-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://product-microservice"))
                .route("tracking-microservice", r -> r.path("/order-management-system/tracking-microservice/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://tracking-microservice"))
                .build();
    }
}
