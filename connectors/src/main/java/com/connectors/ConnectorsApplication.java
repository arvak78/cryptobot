package com.connectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ConnectorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectorsApplication.class, args);
	}
}
