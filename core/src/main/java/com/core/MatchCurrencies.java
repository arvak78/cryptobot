package com.core;

import com.commons.Exchanges;
import com.commons.constants.CacheConstants;
import com.commons.model.BotCurrency;
import com.core.utils.ExchangeMatch;
import com.core.utils.OperationCurrencies;
import com.core.utils.ReadXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Manel on 02/02/2018.
 */
@EnableCaching
@Component
public class MatchCurrencies {

    private OperationCurrencies operationCurrencies = ReadXml.readOperatingCurrencies();

    private Map<Exchanges, ExchangeMatch> matchCurrenciesByExchange;

    @Autowired
    private CacheManager cacheManager;

    public Map<Exchanges, ExchangeMatch> findMatchCurrenciesByExchange() {

        if (matchCurrenciesByExchange != null && matchCurrenciesByExchange.size() > 0) {
            return matchCurrenciesByExchange;
        }

        Map<Exchanges, ExchangeMatch> mapMatch = new HashMap<>();
        for (Exchanges exchange0 : Exchanges.values()) {
            ExchangeMatch match = new ExchangeMatch(exchange0);
            List<String> currencyMatchList = new ArrayList<>();
            for (Exchanges exchange1 : Exchanges.values()) {
                for (com.core.utils.Currency currency : operationCurrencies.getCurrency()) {
                    if (exchange0 != exchange1) {
                        BotCurrency currency0 = cacheManager.getCache(CacheConstants.CURRENCIES).get(exchange0 + currency.getSymbol(), BotCurrency.class);
                        BotCurrency currency1 = cacheManager.getCache(CacheConstants.CURRENCIES).get(exchange1 + currency.getSymbol(), BotCurrency.class);
                        if (currency0 != null && currency1 != null) {
                            currencyMatchList.add(currency.getSymbol());
                        }
                        match.addMatch(exchange1, currencyMatchList);
                    }
                }
            }
            mapMatch.put(exchange0, match);
        }

        matchCurrenciesByExchange = mapMatch;

        return matchCurrenciesByExchange;
    }
}
