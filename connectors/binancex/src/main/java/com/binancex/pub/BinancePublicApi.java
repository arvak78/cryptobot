package com.binancex.pub;

import com.binancex.model.currencies.Response;
import com.binancex.model.prices.Prices;
import com.binancex.parser.BinanceToBot;
import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.BotResponse;
import com.commons.model.ExchangeTasks;
import com.commons.model.ExchangesApi;
import com.commons.model.Wrapper;
import com.commons.utils.JsonClients;
import com.commons.utils.Properties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by manel on 19/01/18.
 */
//@Exchange(name = Exchanges.BINANCE)
public class BinancePublicApi implements ExchangesApi {

    public static final String PROPERTIES_PATH = BinancePublicApi.class.getProtectionDomain().
            getCodeSource().getLocation().getPath();

    @Override
    public BotResponse getCurrencies() throws ExchangeException, MarshallException {

        BotResponse botResponse = null;

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.currencies");

        Wrapper binanceRS = JsonClients.getInstance().build(Response.class, url);

        BinanceToBot toBot = new BinanceToBot();
        botResponse = toBot.parseCurrency(binanceRS);

        return botResponse;

    }

    @Override
    public List<ExchangeTasks> getMarkets() throws MarshallException, ExchangeException {

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.prices");

        Future future = JsonClients.getInstance().buildAsync(Prices[].class, url);



//        BinanceToBot toBot = new BinanceToBot();
//        Map<String, BotPrice> priceMap = toBot.parsePrices(wrapperPrices);

        return null;
    }

}
