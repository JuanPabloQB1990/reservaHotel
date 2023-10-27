package com.reservaHoteles.hoteles.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    private Long cedula;

    @NotNull(message = "el nombre del cliente es obligatorio")
    private String nombre;

    @NotNull(message = "el apellido del cliente es obligatorio")
    private String apellido;

    @NotNull(message = "el ciudad es obligatoria")
    private String ciudad;

    @NotNull(message = "la direccion del cliente es obligatoria")
    private String direccion;

    private int edad;

    @NotNull(message = "el correo del cliente es obligatorio")
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reservas> reservas = new ArrayList<>();


}