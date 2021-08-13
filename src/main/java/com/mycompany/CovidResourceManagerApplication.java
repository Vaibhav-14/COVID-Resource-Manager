package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class CovidResourceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidResourceManagerApplication.class, args);
		
	}
	
}
