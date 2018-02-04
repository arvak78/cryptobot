package com.commons.model;

import com.commons.exceptions.ExchangeException;

import java.util.Map;

/**
 * Created by Manel on 01/02/2018.
 */
public interface ExchangeTasks {

    public Map<String, BotPrice> getData() throws ExchangeException;

}
