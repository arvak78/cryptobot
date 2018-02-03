package com.binancex.model.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prices {

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("askPrice")
	private BigDecimal askPrice;

	@JsonProperty("askQty")
	private BigDecimal askQty;

	@JsonProperty("bidQty")
	private BigDecimal bidQty;

	@JsonProperty("bidPrice")
	private BigDecimal bidPrice;
}