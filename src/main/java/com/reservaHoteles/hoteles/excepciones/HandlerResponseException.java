package com.reservaHoteles.hoteles.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HandlerResponseException extends ResponseStatusException {
    public HandlerResponseException(HttpStatus status, String message){
        super(status,message);
    }
}
