package com.cryptopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cryptopia")
public class CryptopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptopiaApplication.class, args);
	}
}
