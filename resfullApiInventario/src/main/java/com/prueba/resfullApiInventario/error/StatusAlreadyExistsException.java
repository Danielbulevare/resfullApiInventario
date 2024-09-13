package com.prueba.resfullApiInventario.error;

public class StatusAlreadyExistsException extends Exception{
    public StatusAlreadyExistsException(String message) {
        super(message);
    }
}
