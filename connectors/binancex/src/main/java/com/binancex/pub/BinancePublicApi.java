package com.binancex.pub;

import com.binancex.model.Response;
import com.binancex.parser.BinanceToBot;
import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.BotResponse;
import com.commons.model.ExchangesApi;
import com.commons.model.Wrapper;
import com.commons.utils.JsonClients;
import com.commons.utils.Properties;

/**
 * Created by manel on 19/01/18.
 */
@Exchange(name = Exchanges.BINANCE)
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

}
