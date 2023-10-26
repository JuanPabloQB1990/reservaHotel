package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.exceptions.ReservaFindException;
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

    @ExceptionHandler(ReservaFindException.class)
    public Map<String, String> reservaFindExcepcion(ReservaFindException rfe){
        errorMap = new HashMap<>();
        errorMap.put("errorMessage", rfe.getMessage());
        return errorMap;
    }

}
