package com.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapper<T> {

	@JsonProperty("Message")
	private Object message;

	@JsonProperty("Error")
	private Object error;

	@JsonProperty("Data")
	private List<T> data;

	@JsonProperty("Success")
	private boolean success;
}