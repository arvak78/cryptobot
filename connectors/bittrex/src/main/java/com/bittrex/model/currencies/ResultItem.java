package com.bittrex.model.currencies;


import org.codehaus.jackson.annotate.JsonProperty;

public class ResultItem{

	@JsonProperty("Notice")
	private Object notice;

	@JsonProperty("TxFee")
	private double txFee;

	@JsonProperty("IsActive")
	private boolean isActive;

	@JsonProperty("Currency")
	private String currency;

	@JsonProperty("MinConfirmation")
	private int minConfirmation;

	@JsonProperty("BaseAddress")
	private String baseAddress;

	@JsonProperty("CurrencyLong")
	private String currencyLong;

	@JsonProperty("CoinType")
	private String coinType;
}