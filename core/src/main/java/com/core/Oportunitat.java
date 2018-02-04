package com.core;

import com.commons.Exchanges;
import com.commons.model.BotPrice;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Created by Manel on 02/02/2018.
 */
@Component
@Setter
@Getter
public class Oportunitat implements Comparable<Oportunitat> {

    private Exchanges originExchange;
    private Exchanges destinyExchange;
    private String baseCurrency;
    private String quoteCurrency;
    private BotPrice originPrice;
    private BotPrice destinyPrice;
    private Instant exposedInstant;
    private Instant lastPickOutInstant;

    @Override
    public int compareTo(Oportunitat o) {

        EqualsBuilder equals = new EqualsBuilder();

        Boolean isEquals = equals.append(this.originExchange, o.originExchange)
                .append(this.destinyExchange, o.destinyExchange)
                .append(this.baseCurrency, o.baseCurrency)
                .append(this.quoteCurrency, o.quoteCurrency)
                .append(this.originPrice, o.originPrice)
                .append(this.destinyPrice, o.destinyPrice)
                .build();

        return isEquals ? 0 : 1;
    }
}
