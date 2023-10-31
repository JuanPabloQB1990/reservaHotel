package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.excepciones.EntitiesNotFoundExcepcion;
import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.excepciones.ReservaFindException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcepcionController {

    Map<String, String> errorMap;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgumentNull(MethodArgumentNotValidException manve) {
        errorMap = new HashMap<>();
        manve.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntitiesNotFoundExcepcion.class)
    public Map<String, String> entitiesNotFoundExcepcion(EntitiesNotFoundExcepcion rfe){
        errorMap = new HashMap<>();
        errorMap.put("errorMessage", rfe.getMessage());
        return errorMap;
    }


    @ExceptionHandler(HandlerResponseException.class)
    public Map<String, String> clienteNotFoundExcepcion(HandlerResponseException hre){
        errorMap = new HashMap<>();
        errorMap.put("errorMessage", hre.getMessage());
        return errorMap;
    }


    @ExceptionHandler(NullPointerException.class)
    public Map<String, String> clienteNotFoundExcepcion(NullPointerException npe){
        errorMap = new HashMap<>();
        errorMap.put("errorMessage", npe.getMessage());
        return errorMap;
    }
}
