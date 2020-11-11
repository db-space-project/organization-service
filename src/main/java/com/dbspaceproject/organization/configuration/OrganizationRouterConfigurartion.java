package com.dbspaceproject.organization.configuration;

import com.dbspaceproject.organization.handler.OrganizationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class OrganizationRouterConfigurartion {

    @Bean
    public RouterFunction<ServerResponse> organizationRoute(OrganizationHandler organizationHandler) {
        return RouterFunctions
                .route(POST("/organization")
                        .and(contentType(MediaType.APPLICATION_JSON)), organizationHandler::createOrganization)
                .andRoute(GET("/organization/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)), organizationHandler::getById);
    }
}
