package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@Import(ConfigurationRoute.class)
@WebFluxTest
public class SpringControllerTest {

    @MockBean
    private ReservationRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAllReservations()throws Exception {
        Mockito
                .when(repository.findAll())
                .thenReturn(Flux.just(new Reservation("1", "Karel")));

        this.webTestClient
                .get()
                .uri("/reservation")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("@.[0].name").isEqualTo("Karel");
    }
}
