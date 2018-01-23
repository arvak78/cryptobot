package com.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response<T> {

	@JsonProperty("Message")
	private Object message;

	@JsonProperty("Error")
	private Object error;

	@JsonProperty("Data")
	private List<T> data;

	@JsonProperty("Success")
	private boolean success;
}