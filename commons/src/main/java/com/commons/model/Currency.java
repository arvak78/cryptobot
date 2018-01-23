package com.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@Getter
@Setter
public class Currency {

	@JsonProperty("Status")
	private String status;

	@JsonProperty("Symbol")
	private String symbol;

	@JsonProperty("WithdrawFee")
	private double withdrawFee;

	@JsonProperty("DepositConfirmations")
	private int depositConfirmations;

	@JsonProperty("MinTip")
	private double minTip;

	@JsonProperty("IsTipEnabled")
	private boolean isTipEnabled;

	@JsonProperty("Algorithm")
	private String algorithm;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("MaxWithdraw")
	private double maxWithdraw;

	@JsonProperty("StatusMessage")
	private Object statusMessage;

	@JsonProperty("Id")
	private int id;

	@JsonProperty("ListingStatus")
	private String listingStatus;

	@JsonProperty("MinWithdraw")
	private double minWithdraw;

	@JsonProperty("MinBaseTrade")
	private double minBaseTrade;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Currency currency = (Currency) o;

		if (symbol != null ? !symbol.equals(currency.symbol) : currency.symbol != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return symbol != null ? symbol.hashCode() : 0;
	}
}