package com.example.bansalmotors.Bansal.Motors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BansalMotorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BansalMotorsApplication.class, args);
	}

}
