package com.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
public class BotPrice {

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("askPrice")
	private double askPrice;

	@JsonProperty("askQty")
	private double askQty;

	@JsonProperty("bidQty")
	private double bidQty;

	@JsonProperty("bidPrice")
	private double bidPrice;
}