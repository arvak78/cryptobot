package com.cryptopia.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@JsonProperty("Message")
	private Object message;

	@JsonProperty("Error")
	private Object error;

	@JsonProperty("Data")
	private List<Price> data;

	@JsonProperty("Success")
	private boolean success;

}