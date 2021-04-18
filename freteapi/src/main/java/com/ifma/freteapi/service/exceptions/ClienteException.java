package com.ifma.freteapi.service.exceptions;

public class ClienteException extends Exception {
    public ClienteException(Exception e)  throws ClienteException {
        super(e);
    }    
}
