  package com.example.tickety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class TicketyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketyApplication.class, args);
	}
	//sadad
}
