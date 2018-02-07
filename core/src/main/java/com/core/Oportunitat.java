package com.core;

import com.commons.Exchanges;
import com.commons.model.BotPrice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private ZonedDateTime exposedInstant;
    private ZonedDateTime lastPickOutInstant;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<BigDecimal> profitList;

    @Override
    public int compareTo(Oportunitat o) {

        EqualsBuilder equals = new EqualsBuilder();

        Boolean isEquals = equals.append(this.originExchange, o.originExchange)
                .append(this.destinyExchange, o.destinyExchange)
                .append(this.baseCurrency, o.baseCurrency)
                .append(this.quoteCurrency, o.quoteCurrency)
//                .append(this.originPrice, o.originPrice)
//                .append(this.destinyPrice, o.destinyPrice)
                .build();

        return isEquals ? 0 : 1;
    }

    public void addProfitList(BigDecimal profit) {
        if (profitList == null)
            profitList = new ArrayList<>();

        profitList.add(profit);
    }

    public List<BigDecimal> getProfitList() {
        return profitList;
    }
}
