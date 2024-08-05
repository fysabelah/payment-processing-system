package com.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class RoutesConfig {

    @Value("#{'${documentation.path}'.split(',')}")
    private List<String> documentationsPath;

    @Value("${service.address.authentication}")
    private String authenticationUri;

    @Value("${service.address.payment}")
    private String paymentUri;

    @Value("${service.address.customer}")
    private String customerUri;

    @Value("${service.address.card}")
    private String cardUri;

    @Value("${server.servlet.context-path}")
    private String basePath;

    @Bean
    public RouteLocator custom(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("authentication-api",  r -> createRoute(r, basePath + "/autenticacao", authenticationUri))
                .route("authentication-api-users", r -> r.path(basePath + "/usuarios/**")
                        .filters(f-> f.stripPrefix(1))
                        .uri(authenticationUri)
                )
                .route("customer", r -> createRoute(r, basePath + "/cliente", customerUri))
                .route("card", r -> createRoute(r, basePath + "/cartao", cardUri))
                .route("payment", r -> createRoute(r, basePath + "/pagamentos", paymentUri))
                .build();
    }

    private Buildable<Route> createRoute(PredicateSpec route, String path, String uri) {
        String[] documentationsEndpoint = documentationsPath.stream()
                .map(doc -> path + "/" + doc + "/**")
                .toList()
                .toArray(new String[0]);

        return route.path(path + "/**")
                .and().not(p -> p.path(documentationsEndpoint))
                .filters(f -> f.stripPrefix(1))
                .uri(uri);
    }

    @Bean
    public RouteLocator docs(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();

        Map<String, Map<String, String>> pathBaseRoute = Map.of(
                "authentication", Map.of("path", basePath + "/autenticacao", "uri", authenticationUri),
                "client", Map.of("path", basePath + "/cliente", "uri", customerUri),
                "card", Map.of("path", basePath + "/cartao", "uri", cardUri),
                "payment", Map.of("path", basePath + "/pagamentos", "uri", paymentUri)
        );

        for (Map.Entry<String, Map<String, String>> routeConfig : pathBaseRoute.entrySet()) {
            List<String> documentations = getDocumentationPath(routeConfig.getValue().get("path"));

            documentations.forEach(path -> routesBuilder.route(
                            getRouteIdentification(path),
                            createDocumentationRoute(path, routeConfig.getValue().get("uri"))
                    )
            );
        }

        return routesBuilder.build();
    }

    private String getRouteIdentification(String documentationPath) {
        int index = documentationPath.lastIndexOf("/");

        return documentationPath.substring(0, index).replaceAll("/", "-");
    }

    private List<String> getDocumentationPath(String path) {
        return documentationsPath.stream()
                .map(doc -> path + "/" + doc + "/**")
                .toList();
    }

    private Function<PredicateSpec, Buildable<Route>> createDocumentationRoute(String path, String uri) {
        String docPrefix = path.split("/")[3];

        return route -> route.path(path)
                .filters(f -> f.stripPrefix(2)
                        .rewritePath(docPrefix + "(?<segment>.*)", docPrefix + "${segment}"))
                .uri(uri);
    }
}