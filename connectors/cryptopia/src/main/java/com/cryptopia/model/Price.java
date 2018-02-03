package com.cryptopia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Getter
@Setter
public class Price {

	@JsonProperty("High")
	private double high;

	@JsonProperty("BaseVolume")
	private BigDecimal baseVolume;

	@JsonProperty("Label")
	private String label;

	@JsonProperty("Change")
	private BigDecimal change;

	@JsonProperty("BuyBaseVolume")
	private BigDecimal buyBaseVolume;

	@JsonProperty("LastPrice")
	private BigDecimal lastPrice;

	@JsonProperty("SellBaseVolume")
	private BigDecimal sellBaseVolume;

	@JsonProperty("BuyVolume")
	private BigDecimal buyVolume;

	@JsonProperty("Open")
	private BigDecimal open;

	@JsonProperty("BidPrice")
	private BigDecimal bidPrice;

	@JsonProperty("SellVolume")
	private BigDecimal sellVolume;

	@JsonProperty("Low")
	private BigDecimal low;

	@JsonProperty("Volume")
	private BigDecimal volume;

	@JsonProperty("Close")
	private BigDecimal close;

	@JsonProperty("AskPrice")
	private BigDecimal askPrice;

	@JsonProperty("TradePairId")
	private int tradePairId;

}