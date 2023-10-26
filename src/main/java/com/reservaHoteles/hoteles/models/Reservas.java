package com.reservaHoteles.hoteles.models;

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

    @Column(name = "fecha_reserva")

    private String fechaReserva;

    @NotNull(message = "el id del cliente es obligatorio")
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "total_a_pagar")
    private double totalAPagar = 0;

    @NotNull(message = "el id de la habitacion es obligatorio")
    @Column(name = "id_habitacion")
    private Integer idHabitacion;

    public Reservas(String codReserva, String fechaReserva, Integer idCliente, double totalAPagar, Integer idHabitacion) {
        this.codReserva = codReserva;
        this.fechaReserva = fechaReserva.format(String.valueOf(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
        this.idCliente = idCliente;
        this.totalAPagar = totalAPagar;
        this.idHabitacion = idHabitacion;
    }
}
