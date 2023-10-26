package com.reservaHoteles.hoteles.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservaConfirmationList {

    public String codReserva;

    public String fechaReserva;

    public double totalAPagar;

    public Integer idHabitacion;

}
