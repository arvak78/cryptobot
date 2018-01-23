package com.commons.exceptions;

/**
 * Created by manel on 20/01/18.
 */
public class ExchangeException extends Exception {

    public ExchangeException() {}

    public ExchangeException(String message) {
        super(message);
    }

    public String expJsonErrorExchange() {
        return "Error al recuperar datos del exchange";
    }
}
