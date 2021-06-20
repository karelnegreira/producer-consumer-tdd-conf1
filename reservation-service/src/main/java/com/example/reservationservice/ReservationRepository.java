package com.example.reservationservice;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
    Flux<Reservation> findByName(String name);
}
