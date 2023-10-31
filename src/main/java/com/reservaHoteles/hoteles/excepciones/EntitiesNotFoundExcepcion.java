package com.reservaHoteles.hoteles.excepciones;

public class EntitiesNotFoundExcepcion extends RuntimeException{
    public EntitiesNotFoundExcepcion(String message) {
        super(message);
    }
}
