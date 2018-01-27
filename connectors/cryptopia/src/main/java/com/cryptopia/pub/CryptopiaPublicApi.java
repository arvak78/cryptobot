package com.cryptopia.pub;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.ExchangesApi;
import com.commons.model.BotResponse;
import com.commons.model.Wrapper;
import com.commons.utils.JsonClients;
import com.commons.utils.Properties;
import com.cryptopia.model.Currency;
import com.cryptopia.parser.CryptopiaToBot;
import com.fasterxml.jackson.core.type.TypeReference;

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
                buildWithMapper(new TypeReference<Wrapper<Currency>>() {}, url);

        CryptopiaToBot toBot = new CryptopiaToBot();
        BotResponse botResponse = toBot.parseCurrencies(cryptopiaResponse);

        return botResponse;

    }

}
