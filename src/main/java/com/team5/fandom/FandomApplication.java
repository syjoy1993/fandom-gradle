package com.team5.fandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FandomApplication {

	public static void main(String[] args) {
		SpringApplication.run(FandomApplication.class, args);
	}

}
