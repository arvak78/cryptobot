package com.cryptopia.pub;

import com.commons.Exchanges;
import com.commons.annotations.Exchange;
import com.commons.exceptions.MarshallException;
import com.commons.model.ExchangesApi;
import com.commons.model.Response;
import com.commons.utils.Properties;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by manel on 19/01/18.
 */
@Exchange(name = Exchanges.CRYPTOPIA)
public class CryptopiaPublicApi implements ExchangesApi {

    public static final String PROPERTIES_PATH = CryptopiaPublicApi.class.getProtectionDomain().
            getCodeSource().getLocation().getPath();

    @Override
    public Response getCurrencies() throws MarshallException {

        Response response = null;

        String url = Properties.getApplicationProperties(PROPERTIES_PATH)
                .getProperty("url.currencies");

        try {
            Client client = ClientBuilder.newClient();

            //TODO: Se deberia poder hacer el unmarshall desde aqui
            String currency = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);

            ObjectMapper mapper = new ObjectMapper();

            response = mapper.readValue(currency, Response.class);
        } catch (IOException e) {
            throw new MarshallException();
        }

        return response;

    }

}
