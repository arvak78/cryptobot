package com.binancex.model.currencies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltersItem{

	@JsonProperty("minPrice")
	private String minPrice;

	@JsonProperty("maxPrice")
	private String maxPrice;

	@JsonProperty("filterType")
	private String filterType;

	@JsonProperty("tickSize")
	private String tickSize;

	@JsonProperty("minQty")
	private double minQty;

	@JsonProperty("maxQty")
	private double maxQty;

	@JsonProperty("stepSize")
	private double stepSize;

	@JsonProperty("minNotional")
	private double minNotional;

}