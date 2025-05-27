package com.example.web_rise.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;

@SpringBootApplication
@RequiredArgsConstructor
public class WebRiseTestApplication {

	private final Flyway flyway;

	public static void main(String[] args) {
		SpringApplication.run(WebRiseTestApplication.class, args);
	}

	@PostConstruct
	void migrate() {
		flyway.migrate();
	}
}
