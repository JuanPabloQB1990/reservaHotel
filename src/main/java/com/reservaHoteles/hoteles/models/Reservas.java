package com.reservaHoteles.hoteles.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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
