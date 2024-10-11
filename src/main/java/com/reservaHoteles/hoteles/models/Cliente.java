package com.reservaHoteles.hoteles.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
@NoArgsConstructor
public class Cliente {

    @Id
    @NotNull(message = "la cédula es obligatorio")
    private Long cedula;

    @NotNull(message = "el nombre del cliente es obligatorio")
    private String nombre;

    @NotNull(message = "el apellido del cliente es obligatorio")
    private String apellido;

    @NotNull(message = "el ciudad es obligatoria")
    private String ciudad;

    private String direccion;

    private int edad;

    private String correoElectronico;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente(Long cedula, String nombre, String apellido, String ciudad, String direccion, int edad, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
    }
}