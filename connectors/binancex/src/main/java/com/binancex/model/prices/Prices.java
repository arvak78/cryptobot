package com.binancex.model.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prices {

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("askPrice")
	private String askPrice;

	@JsonProperty("askQty")
	private String askQty;

	@JsonProperty("bidQty")
	private String bidQty;

	@JsonProperty("bidPrice")
	private String bidPrice;
}