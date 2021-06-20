package com.example.reservationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
class ConfigurationRoute {
    private final ReservationRepository repository;

    ConfigurationRoute(ReservationRepository repository) {
        this.repository = repository;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(RequestPredicates.GET("/reservation"),
                serverRequest -> ServerResponse.ok().body(repository.findAll(), Reservation.class));
    }
}
