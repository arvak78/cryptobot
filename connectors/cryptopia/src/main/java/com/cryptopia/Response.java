package com.cryptopia;

import com.model.Currency;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manel on 19/01/18.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Response {

    private String Success;
    private String Message;
    private Currency Data;

}
