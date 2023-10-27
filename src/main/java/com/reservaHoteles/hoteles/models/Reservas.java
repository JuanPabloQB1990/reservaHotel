package com.reservaHoteles.hoteles.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Data
@Table(name = "reservas")
@NoArgsConstructor
public class Reservas {

    @Id
    @Column(name = "id_reserva")
    private String codReserva = UUID.randomUUID().toString().toUpperCase();

    @NotNull(message = "la fecha es obligatoria")
    @Column(name = "fecha_reserva")
    private String fechaReserva;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedulaCliente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;

    @Column(name = "total_a_pagar")
    private double totalAPagar = 0;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "numeroHabitacion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Habitacion habitacion;

    public Reservas(String codReserva, String fechaReserva, Cliente cliente, double totalAPagar, Habitacion habitacion) {
        this.codReserva = codReserva;
        this.fechaReserva = fechaReserva.format(String.valueOf(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
        this.cliente = cliente;
        this.totalAPagar = totalAPagar;
        this.habitacion = habitacion;
    }
}
