package com.commons.model;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BotCurrency {
	private String status;
	private String symbol;
	private String algorithm;
	private String name;
	private String listingStatus;
	private Set<String> quotes = new HashSet<>();
	private int depositConfirmations; // BORRAR
	private int id; // BORRAR
	private double maxQty;
	private double minQty;
	private double withdrawFee;
	private double minTip; // BORRAR
	private double maxWithdraw;
	private boolean isTipEnabled; // BORRAR
	private double minWithdraw;
	private double minBaseTrade; // BORRAR
	private Object statusMessage; // BORRAR

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BotCurrency currency = (BotCurrency) o;

		if (symbol != null ? !symbol.equals(currency.symbol) : currency.symbol != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return symbol != null ? symbol.hashCode() : 0;
	}
}