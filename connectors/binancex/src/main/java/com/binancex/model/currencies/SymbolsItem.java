package com.binancex.model.currencies;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SymbolsItem{

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("quotePrecision")
	private int quotePrecision;

	@JsonProperty("orderTypes")
	private List<String> orderTypes;

	@JsonProperty("baseAsset")
	private String baseAsset;

	@JsonProperty("filters")
	private List<FiltersItem> filters;

	@JsonProperty("baseAssetPrecision")
	private int baseAssetPrecision;

	@JsonProperty("icebergAllowed")
	private boolean icebergAllowed;

	@JsonProperty("quoteAsset")
	private String quoteAsset;

	@JsonProperty("status")
	private String status;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setQuotePrecision(int quotePrecision){
		this.quotePrecision = quotePrecision;
	}

	public int getQuotePrecision(){
		return quotePrecision;
	}

	public void setOrderTypes(List<String> orderTypes){
		this.orderTypes = orderTypes;
	}

	public List<String> getOrderTypes(){
		return orderTypes;
	}

	public void setBaseAsset(String baseAsset){
		this.baseAsset = baseAsset;
	}

	public String getBaseAsset(){
		return baseAsset;
	}

	public void setFilters(List<FiltersItem> filters){
		this.filters = filters;
	}

	public List<FiltersItem> getFilters(){
		return filters;
	}

	public void setBaseAssetPrecision(int baseAssetPrecision){
		this.baseAssetPrecision = baseAssetPrecision;
	}

	public int getBaseAssetPrecision(){
		return baseAssetPrecision;
	}

	public void setIcebergAllowed(boolean icebergAllowed){
		this.icebergAllowed = icebergAllowed;
	}

	public boolean isIcebergAllowed(){
		return icebergAllowed;
	}

	public void setQuoteAsset(String quoteAsset){
		this.quoteAsset = quoteAsset;
	}

	public String getQuoteAsset(){
		return quoteAsset;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"SymbolsItem{" + 
			"symbol = '" + symbol + '\'' + 
			",quotePrecision = '" + quotePrecision + '\'' + 
			",orderTypes = '" + orderTypes + '\'' + 
			",baseAsset = '" + baseAsset + '\'' + 
			",filters = '" + filters + '\'' + 
			",baseAssetPrecision = '" + baseAssetPrecision + '\'' + 
			",icebergAllowed = '" + icebergAllowed + '\'' + 
			",quoteAsset = '" + quoteAsset + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}