package com.core.utils;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by manel on 23/01/18.
 */
@Getter
@Setter
@XmlRootElement(name="currencies")
public class OperationCurrencies {
    private List<Currency> currency;
}
