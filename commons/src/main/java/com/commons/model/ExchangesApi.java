package com.commons.model;

import com.commons.exceptions.MarshallException;

/**
 * Created by manel on 20/01/18.
 */
public interface ExchangesApi {
    public Response getCurrencies() throws MarshallException;
}
