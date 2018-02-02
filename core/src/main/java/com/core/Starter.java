package com.core;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.constants.CacheConstants;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.BotCurrency;
import com.commons.model.BotResponse;
import com.commons.model.ExchangesApi;
import com.commons.utils.reflection.FindAnotations;
import com.core.cache.ConnectorService;
import com.core.cache.CurrencyService;
import com.core.utils.ExchangeMatch;
import com.core.utils.OperationCurrencies;
import com.core.utils.ReadXml;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@EnableCaching
@Service
public class Starter {

    private OperationCurrencies operationCurrencies;
    private Map<Exchanges, ExchangeMatch> matchCurrenciesByExchange;

    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CacheManager cacheManager;

    private Map<Exchanges, Class> allClassesAnnotatedBy;


    public void initConfig() throws MarshallException, ExchangeException {

        allClassesAnnotatedBy = FindAnotations.getInstance().findAllClassesAnnotatedBy(Exchange.class);
        initConnectorsAndCurrencies(allClassesAnnotatedBy);
        findMatchCurrenciesByExchange();
    }


    private void initConnectorsAndCurrencies(Map<Exchanges, Class> allClassesAnnotatedBy) throws MarshallException, ExchangeException {
        for (Map.Entry<Exchanges, Class> entry : allClassesAnnotatedBy.entrySet()) {
            Exchanges key = entry.getKey();
            Class aClass = entry.getValue();

            // Add to cache all classes retrieved from Connectors
            connectorService.play(key, aClass);

            // Puede que no sea necesario
            try {
                ExchangesApi api = (ExchangesApi) aClass.newInstance();
                BotResponse<BotCurrency> response = api.getCurrencies();

                addToCacheCurrenciesByExchange(key, response.getData());

            } catch (InstantiationException|IllegalAccessException|ExchangeException e) {
                e.printStackTrace();
            }

        }

        operationCurrencies = ReadXml.readOperatingCurrencies();

    }

    private void addToCacheCurrenciesByExchange(Exchanges key, List<BotCurrency> data) {
        for (BotCurrency currency : data) {
            currencyService.play(key, currency);
        }
    }

    private void findMatchCurrenciesByExchange() {
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
    }

}
