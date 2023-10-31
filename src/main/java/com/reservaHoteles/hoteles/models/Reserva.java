package com.reservaHoteles.hoteles.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "reservas")
@NoArgsConstructor
public class Reserva {

    @Id
    @Column(name = "cod_reserva")
    private String codReserva = UUID.randomUUID().toString().toUpperCase();

    @NotNull(message = "la fecha es obligatoria")
    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_cliente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;

    private double total = 0.0;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "numero_habitacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Habitacion habitacion;

    public Reserva(String codReserva, LocalDateTime fechaReserva, Cliente cliente, double total, Habitacion habitacion) {
        this.codReserva = codReserva;
        this.fechaReserva = fechaReserva;
        this.cliente = cliente;
        this.total = total;
        this.habitacion = habitacion;
    }


}
