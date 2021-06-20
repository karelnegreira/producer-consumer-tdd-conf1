package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SpringMongoDbTest {
    @Autowired
    private ReactiveMongoTemplate mongodb;

    @Test
    public void testShouldStoreAndRetrieve() {
        Mono<Reservation> reservationSaved = mongodb.save(new Reservation(null, "Karel"));

        StepVerifier.create(reservationSaved)
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("Karel"))
                .verifyComplete();
    }
}
