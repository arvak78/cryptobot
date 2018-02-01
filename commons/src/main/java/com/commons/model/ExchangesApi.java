package com.commons.model;

import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;

import java.util.List;

/**
 * Created by manel on 20/01/18.
 */
public interface ExchangesApi {
    public BotResponse<BotCurrency> getCurrencies() throws MarshallException, ExchangeException;
    public List<ExchangeTasks> getMarkets() throws MarshallException, ExchangeException;
}
