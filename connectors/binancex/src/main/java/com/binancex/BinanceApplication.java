package com.binancex;

import com.binancex.pub.BinancePublicApi;
import com.commons.exceptions.ExchangeException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BinanceApplication {

	public static void main(String[] args) throws ExchangeException {
		SpringApplication.run(BinanceApplication.class, args);
		BinancePublicApi api = new BinancePublicApi();
		api.getCurrencies();
	}
}
