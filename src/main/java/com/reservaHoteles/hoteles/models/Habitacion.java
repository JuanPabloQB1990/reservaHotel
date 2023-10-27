package com.reservaHoteles.hoteles.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    private Long numero;

    @NotNull(message = "tipo de habitacion es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "el precio de la habitacion es obligatorio")
    private double precioBase;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Reservas> reservas = new ArrayList<>();

}
