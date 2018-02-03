package com.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Getter
@Setter
public class BotPrice {

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