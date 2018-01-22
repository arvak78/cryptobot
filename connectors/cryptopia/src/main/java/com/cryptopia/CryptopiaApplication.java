package com.cryptopia;

import com.commons.exceptions.MarshallException;
import com.cryptopia.pub.CryptopiaPublicApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cryptopia")
public class CryptopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptopiaApplication.class, args);

		CryptopiaPublicApi app = new CryptopiaPublicApi();
		try {
			app.getCurrencies();
		} catch (MarshallException e) {
			e.printStackTrace();
		}
	}


}
