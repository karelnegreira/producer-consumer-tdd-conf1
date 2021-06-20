package com.example.reservationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReservationServiceApplication {

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Reservation {
	private String id;
	private String name;
}
