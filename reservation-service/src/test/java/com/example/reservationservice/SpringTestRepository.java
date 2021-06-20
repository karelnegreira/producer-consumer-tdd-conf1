package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SpringTestRepository {
    @Autowired
    private ReservationRepository reps;

    @Test
    public void testSaveAndFinds() {
        Mono<Reservation> savedReservation = reps.save(new Reservation(null, "Karel"));
        Publisher<Reservation> foundReservation = savedReservation.thenMany(reps.findById("60c61de65d5702321b65d878"));

        StepVerifier.create(foundReservation)
                .expectNextMatches(reservation -> reservation.getName().equalsIgnoreCase("Karel"))
                .verifyComplete();
    }
}
