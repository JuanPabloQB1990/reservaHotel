package com.reservaHoteles.hoteles.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaConfirmation {

    public String codReserva;

    public LocalDateTime fechaReserva;

    public double totalAPagar;

    public Long numeroHabitacion;

    private String tipoHabitacion;

    private double precioBase;

    public ReservaConfirmation(String codReserva, LocalDateTime fechaReserva, double totalAPagar, Long numeroHabitacion, String tipoHabitacion, double precioBase) {
        this.codReserva = codReserva;
        this.fechaReserva = fechaReserva;
        this.totalAPagar = totalAPagar;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioBase = precioBase;
    }
}
