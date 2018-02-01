package com.commons.utils;

import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import com.commons.model.Wrapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Future;

/**
 * Created by Manel on 27/01/2018.
 */
public class JsonClients<T>{
    private static JsonClients ourInstance = new JsonClients();

    public static JsonClients getInstance() {
        return ourInstance;
    }

    private JsonClients() {
        this.client = ClientBuilder.newClient();
    }

    private Client client;

    public Wrapper<T> buildWithMapper(TypeReference type, String url) throws MarshallException, ExchangeException {

        Wrapper<T> response = null;

        try {

            //TODO: Se deberia poder hacer el unmarshall desde aqui
            String currency = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            response = mapper.readValue(currency, type);

        } catch (JsonParseException|JsonMappingException e) {
            throw new MarshallException("Error en el unmarshall de: " + url);
        } catch (IOException e) {
            throw new MarshallException("Error en el unmarshall de: " + url);
        } catch (IllegalArgumentException|NullPointerException e) {
            throw new ExchangeException("Error en la llamada al exchange: " + url);
        }

        return response;
    }

    public Wrapper<T> build(Class<T> data, String url) throws MarshallException, ExchangeException {

        Wrapper<T> exchangeResponse = new Wrapper<>();

        try {

            T t = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get(data);

            exchangeResponse.setSuccess(true);
//            exchangeResponse.setData(Collections.singletonList(generic));

        } catch (Exception e) {
            throw new ExchangeException("Error en la llamada al exchange: " + url);
        }

        return exchangeResponse;
    }

    public Future<T> buildAsync(Class<T> data, String url) throws MarshallException, ExchangeException {

        Future<T> future = null;

        try {

            future = client.target(url)
                    .request(MediaType.APPLICATION_JSON).async()
                    .get(data);

//            exchangeResponse.setSuccess(true);
//            exchangeResponse.setData(Collections.singletonList(generic));

        } catch (Exception e) {
            throw new ExchangeException("Error en la llamada al exchange: " + url);
        }

        return future;
    }

    public Future<T> buildWithMapperAsync(TypeReference type, String url) throws MarshallException, ExchangeException {

        Wrapper<T> response = null;

        try {

            //TODO: Se deberia poder hacer el unmarshall desde aqui
            client.getConfiguration().getPropertyNames();
            Future<String> currency = client.target(url)
                    .request(MediaType.APPLICATION_JSON).async()
                    .get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//            response = mapper.readValue(currency, type);


        } catch (Exception e) {
            throw new ExchangeException("Error en la llamada al exchange: " + url);
        }

        return null;
    }


    public void closeClient() {
        client.close();
    }
}
