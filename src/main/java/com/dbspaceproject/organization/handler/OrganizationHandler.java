package com.dbspaceproject.organization.handler;

import com.dbspaceproject.organization.events.OrganizationCreatedEvent;
import com.dbspaceproject.organization.models.OrganizationModel;
import com.dbspaceproject.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationHandler {
    private final ReactiveCircuitBreakerFactory circuitBreakerFactory;
    private final ApplicationEventPublisher publisher;
    private final OrganizationRepository organizationRepository;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> getById(ServerRequest request) {
        String organizationId = request.pathVariable("id");
        Mono<OrganizationModel> organizationModelMono = organizationRepository.findById((organizationId));
        return organizationModelMono
                .flatMap(organizationModel -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(organizationModel))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createOrganization(ServerRequest request) {
        System.out.println(request.headers());
        Mono<OrganizationModel> organizationModelMono = request.bodyToMono(OrganizationModel.class);
        return this.circuitBreakerFactory.create("delay").run(organizationModelMono
                .flatMap(organizationModel -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(organizationRepository.save(organizationModel)
                                .doOnSuccess(organization -> this.publisher.publishEvent(new OrganizationCreatedEvent(organization))), OrganizationModel.class))
                .switchIfEmpty(notFound));
    }
}
