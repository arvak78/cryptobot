package com.core.utils;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manel on 23/01/18.
 */
@Getter
@Setter
@XmlRootElement(name = "currency")
public class Currency {
    private String symbol;
    private String trust;
}
