package com.commons.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;

import javax.ws.rs.core.Feature;

import static org.junit.Assert.*;

/**
 * Created by Manel on 28/01/2018.
 */
public class JsonClientsTest {

    @Test
    public void name() throws Exception {

        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        defaultClientConfig.getFeatures().put("FAIL_ON_UNKNOWN_PROPERTIES", false);
        Client client = Client.create(defaultClientConfig);

//            Client client = Client.create();

            WebResource webResource = client
                    .resource("https://www.cryptopia.co.nz/api/GetMarkets");

        Response response = webResource.accept("application/json")
                .get(Response.class);

        System.out.println("");

    }
}