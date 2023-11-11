package com.example.neobis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages = "com.example.neobis.entity")
public class NeobisApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeobisApplication.class, args);
	}

}

