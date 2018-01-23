package com.binancex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RateLimitsItem{

	@JsonProperty("limit")
	private int limit;

	@JsonProperty("interval")
	private String interval;

	@JsonProperty("rateLimitType")
	private String rateLimitType;

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setInterval(String interval){
		this.interval = interval;
	}

	public String getInterval(){
		return interval;
	}

	public void setRateLimitType(String rateLimitType){
		this.rateLimitType = rateLimitType;
	}

	public String getRateLimitType(){
		return rateLimitType;
	}

	@Override
 	public String toString(){
		return 
			"RateLimitsItem{" + 
			"limit = '" + limit + '\'' + 
			",interval = '" + interval + '\'' + 
			",rateLimitType = '" + rateLimitType + '\'' + 
			"}";
		}
}