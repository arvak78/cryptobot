package com.commons.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<E> {

	@JsonProperty("Message")
	private Object message;

	@JsonProperty("Error")
	private Object error;

	@JsonProperty("Data")
	private Price[] data;

	@JsonProperty("Success")
	private boolean success;

}