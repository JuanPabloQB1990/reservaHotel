package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reservas, String> {

    @Transactional
    @Modifying
    @Query(value = "insert into (id_reserva, fecha_reserva, cedula_cliente, total_a_pagar,numero_habitacion)value" +
            "(:codigo, :fechaReserva, :cedulaCliente, :total_a_pagar, :numeroHabitacion)", nativeQuery = true)
    void crearReserva(@Param("codigo") String codigo, @Param("fechaReserva") String fechaReserva, @Param("cedulaCliente") Long cedulaCliente,
                      @Param("total_a_pagar") Double total_a_pagar, @Param("numeroHabitacion") Long numeroHabitacion);
    @Query(value = "select * from reservas where numero_habitacion = :id and date(fecha_reserva) = :fecha", nativeQuery = true)
    Reservas buscarReserva(@Param("id")Long id, @Param("fecha") String fecha);
}
