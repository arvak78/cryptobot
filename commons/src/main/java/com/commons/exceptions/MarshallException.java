package com.commons.exceptions;

/**
 * Created by manel on 20/01/18.
 */
public class MarshallException extends Exception {

    public MarshallException() {}

    public MarshallException(String message) {
        super(message);
    }

    public String expJsonErrorUnmarshalling() {
        return "Error al transformar de Json a Objeto java en la clase: ";
    }
}
