package com.bittrex.pub;


import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.BotResponse;
import com.commons.model.ExchangeTasks;
import com.commons.model.ExchangesApi;
import com.commons.utils.Properties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manel on 19/01/18.
 */
//@Exchange(name = Exchanges.BITTREX)
public class BittrexPublicApi implements ExchangesApi {

    public static final String PROPERTIES_PATH = BittrexPublicApi.class.getProtectionDomain().
            getCodeSource().getLocation().getPath();

    @Override
    public BotResponse getCurrencies() throws ExchangeException, MarshallException {

        BotResponse botResponse = null;



        System.out.println("Binance Currencies: " + botResponse.getData().size());

        return botResponse;

    }

    @Override
    public List<ExchangeTasks> getMarkets() throws MarshallException, ExchangeException {

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.prices");
        List<ExchangeTasks> tasks = new ArrayList<>();



        return tasks;
    }

}
