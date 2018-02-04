package com.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Getter
@Setter
public class BotPrice implements Comparable<BotPrice>{

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

	@Override
	public int compareTo(BotPrice o) {

		EqualsBuilder equals = new EqualsBuilder();

		Boolean isEquals = equals.append(this.symbol, o.symbol)
				.append(this.askPrice, o.askPrice)
				.append(this.askQty, o.askQty)
				.append(this.bidQty, o.bidQty)
				.append(this.bidPrice, o.bidPrice).
						build();

		return isEquals ? 0 : 1;
	}
}