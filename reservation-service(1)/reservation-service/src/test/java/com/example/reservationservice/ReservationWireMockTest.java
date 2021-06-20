package com.example.reservationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8080)
@Import({ReservationServiceApplication.class, ReservationClient.class})
@AutoConfigureJsonTesters
public class ReservationWireMockTest {

    @Autowired
    ReservationClient client;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(Arrays.asList(new Reservation("1", "Josh")));

        WireMock
                .stubFor(WireMock.get("/reservation")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(json)));
    }

    @Test
    public void testShouldClientReturn() {
        StepVerifier.create(client.getAllReservations())
                .expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("Josh"))
                .expectComplete()
                .log()
                .verify();
    }
}
