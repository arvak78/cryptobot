package com.cryptopia;

import com.cryptopia.pub.CryptopiaPublicApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptopiaApplication.class, args);
		CryptopiaPublicApi app = new CryptopiaPublicApi();
		app.getCurrencies();
	}
}
