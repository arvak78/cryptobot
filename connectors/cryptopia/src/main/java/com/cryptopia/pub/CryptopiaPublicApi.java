package com.cryptopia.pub;

import com.cryptopia.Response;
import com.model.Currency;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Created by manel on 19/01/18.
 */
public class CryptopiaPublicApi {

    public void getCurrencies() {
        String REST_URI
                = "https://www.cryptopia.co.nz/api/GetCurrencies";

        Client client = ClientBuilder.newClient();

        Response currency = client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println("");
    }
}
