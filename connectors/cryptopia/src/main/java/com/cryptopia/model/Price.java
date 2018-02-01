package com.cryptopia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
public class Price {

	@JsonProperty("High")
	private double high;

	@JsonProperty("BaseVolume")
	private double baseVolume;

	@JsonProperty("Label")
	private String label;

	@JsonProperty("Change")
	private double change;

	@JsonProperty("BuyBaseVolume")
	private double buyBaseVolume;

	@JsonProperty("LastPrice")
	private double lastPrice;

	@JsonProperty("SellBaseVolume")
	private double sellBaseVolume;

	@JsonProperty("BuyVolume")
	private double buyVolume;

	@JsonProperty("Open")
	private double open;

	@JsonProperty("BidPrice")
	private double bidPrice;

	@JsonProperty("SellVolume")
	private double sellVolume;

	@JsonProperty("Low")
	private double low;

	@JsonProperty("Volume")
	private double volume;

	@JsonProperty("Close")
	private double close;

	@JsonProperty("AskPrice")
	private double askPrice;

	@JsonProperty("TradePairId")
	private int tradePairId;

	@Override
 	public String toString(){
		return 
			"Price{" +
			"high = '" + high + '\'' + 
			",baseVolume = '" + baseVolume + '\'' + 
			",label = '" + label + '\'' + 
			",change = '" + change + '\'' + 
			",buyBaseVolume = '" + buyBaseVolume + '\'' + 
			",lastPrice = '" + lastPrice + '\'' + 
			",sellBaseVolume = '" + sellBaseVolume + '\'' + 
			",buyVolume = '" + buyVolume + '\'' + 
			",open = '" + open + '\'' + 
			",bidPrice = '" + bidPrice + '\'' + 
			",sellVolume = '" + sellVolume + '\'' + 
			",low = '" + low + '\'' + 
			",volume = '" + volume + '\'' + 
			",close = '" + close + '\'' + 
			",askPrice = '" + askPrice + '\'' + 
			",tradePairId = '" + tradePairId + '\'' + 
			"}";
		}
}