package com.core;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.constants.CacheConstants;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.Currency;
import com.commons.model.ExchangesApi;
import com.commons.model.Response;
import com.core.cache.ConnectorService;
import com.core.cache.CurrencyService;
import com.commons.utils.reflection.FindAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

/**
 * Created by manel on 20/01/18.
 */
@EnableCaching
@Controller
public class CoreController {

    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private CurrencyService currencyService;


    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/core")
    public void startExchanges() throws MarshallException, IOException {

        Map<Exchanges, Class> allClassesAnnotatedBy = FindAnnotation.findAllClassesAnnotatedBy(Exchange.class);

        initConnectorsAndCurrencies(allClassesAnnotatedBy);

        // Get from cache
        cacheManager.getCache(CacheConstants.CONNECTORS).get(Exchanges.CRYPTOPIA);
        cacheManager.getCache(CacheConstants.CURRENCIES).get(Exchanges.CRYPTOPIA + "BTC");
    }

    /**
     * Retrieve connector classes and store in cache
     * Retrieve currencies from exchange and store in cache
     * @param allClassesAnnotatedBy
     * @throws MarshallException
     */
    private void initConnectorsAndCurrencies(Map<Exchanges, Class> allClassesAnnotatedBy) throws MarshallException {
        for (Map.Entry<Exchanges, Class> entry : allClassesAnnotatedBy.entrySet()) {
            Exchanges key = entry.getKey();
            Class aClass = entry.getValue();

            // Add to cache all classes retrieved from Connectors
            connectorService.play(key, aClass);

            try {
                ExchangesApi api = (ExchangesApi) aClass.newInstance();
                Response currencies = api.getCurrencies();

                addToCacheCurrenciesByExchange(key, currencies);

            } catch (InstantiationException|IllegalAccessException|ExchangeException e) {
                e.printStackTrace();
            }

        }
    }

    private void addToCacheCurrenciesByExchange(Exchanges key, Response currencies) {
        for (Currency currency : currencies.getData()) {
            currencyService.play(key, currency);
        }
    }

}
