package com.binancex.model.currencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response{

	@JsonProperty("rateLimits")
	private List<RateLimitsItem> rateLimits;

	@JsonProperty("exchangeFilters")
	private List<Object> exchangeFilters;

	@JsonProperty("timezone")
	private String timezone;

	@JsonProperty("serverTime")
	private long serverTime;

	@JsonProperty("symbols")
	private List<SymbolsItem> symbols;

}