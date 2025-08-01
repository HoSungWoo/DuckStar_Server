package com.duckstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DuckstarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuckstarApplication.class, args);
	}
}
