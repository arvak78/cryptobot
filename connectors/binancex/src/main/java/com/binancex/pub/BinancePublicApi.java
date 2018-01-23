package com.binancex.pub;

import com.binancex.parser.BinanceToBot;
import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.model.ExchangesApi;
import com.commons.model.Response;
import com.commons.utils.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Created by manel on 19/01/18.
 */
@Exchange(name = Exchanges.BINANCE)
public class BinancePublicApi implements ExchangesApi {

    public static final String PROPERTIES_PATH = BinancePublicApi.class.getProtectionDomain().
            getCodeSource().getLocation().getPath();

    @Override
    public Response getCurrencies() throws ExchangeException {

        Response response = new Response();

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.currencies");

        try {
            Client client = ClientBuilder.newClient();

            com.binancex.model.Response binanceResponse = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get(com.binancex.model.Response.class);

            BinanceToBot toBot = new BinanceToBot();
            response = toBot.parseCurrency(binanceResponse);

        } catch (Exception e) {
            throw new ExchangeException();
        }

        return response;

    }

}
