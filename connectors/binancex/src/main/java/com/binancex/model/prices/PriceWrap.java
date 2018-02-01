package com.binancex.model.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Manel on 27/01/2018.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceWrap {
    @JsonProperty("")
    private List<Prices> prices;
}
