package com.core.utils;

import com.commons.Exchanges;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Class to store one exchange and a list of exchanges with
 * match currencies for everyone
 */
@Getter
@Setter
public class ExchangeMatch {

    private Exchanges exchange;
    private Map<Exchanges, List<String>> exchangeMapMatch = new HashMap<>();

    public ExchangeMatch(Exchanges exchange) {
        this.exchange = exchange;
    }

    public void addMatch(Exchanges exchangeMatch, List<String> currencyList) {
        exchangeMapMatch.putIfAbsent(exchangeMatch, currencyList);
    }
}
