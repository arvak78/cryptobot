package com.cryptopia.pub;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.BotResponse;
import com.commons.model.ExchangeTasks;
import com.commons.model.ExchangesApi;
import com.commons.model.Wrapper;
import com.commons.utils.JsonClient;
import com.commons.utils.JsonClients;
import com.commons.utils.Properties;
import com.cryptopia.CryptopiaPricesTask;
import com.cryptopia.model.Currency;
import com.cryptopia.model.Price;
import com.cryptopia.parser.CryptopiaToBot;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;

/**
 * Created by manel on 19/01/18.
 */
@Exchange(name = Exchanges.CRYPTOPIA)
public class CryptopiaPublicApi implements ExchangesApi {

    public static final String PROPERTIES_PATH = CryptopiaPublicApi.class.getProtectionDomain().
            getCodeSource().getLocation().getPath();

    @Override
    public BotResponse getCurrencies() throws MarshallException, ExchangeException {

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.currencies");

        Wrapper cryptopiaResponse = JsonClients.getInstance().
                buildWithWrapperMapper(new TypeReference<Wrapper<Currency>>() {}, url);

        CryptopiaToBot toBot = new CryptopiaToBot();
        BotResponse botResponse = toBot.parseCurrencies(cryptopiaResponse);

        return botResponse;

    }

    @Override
    public List<ExchangeTasks> getMarkets() throws MarshallException, ExchangeException {

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.prices");
        List<ExchangeTasks> tasks = new ArrayList<>();

        JsonClient cli = new JsonClient();
        try {
            ParameterizedTypeReference<Wrapper<Price>> typeReference = new ParameterizedTypeReference<Wrapper<Price>>() {};
            tasks.add(new CryptopiaPricesTask(cli.asyncClient(typeReference, url)));
        } catch (IOException e) {
            throw new ExchangeException();
        }

        return tasks;
    }

}
