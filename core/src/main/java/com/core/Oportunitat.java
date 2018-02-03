package com.core;

import com.commons.Exchanges;
import com.commons.model.BotPrice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by Manel on 02/02/2018.
 */
@Component
@Setter
@Getter
public class Oportunitat {

    private Exchanges originExchange;
    private Exchanges destinyExchange;
    private String baseCurrency;
    private String quoteCurrency;
    private BotPrice originPrice;
    private BotPrice destinyPrice;
}
