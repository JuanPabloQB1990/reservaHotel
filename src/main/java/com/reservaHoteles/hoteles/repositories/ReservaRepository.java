package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reservas, String> {

    @Transactional
    @Modifying
    @Query(value = "insert into reservas(cod_reserva, fecha_reserva, cedula_cliente, total, numero_habitacion)value(:codigo, :fechaReserva, :cedulaCliente, :total, :numerohabitacion)", nativeQuery = true)
    void crearReserva(@Param("codigo") String codigo, @Param("fechaReserva") LocalDateTime fechaReserva, @Param("cedulaCliente") Long cedulaCliente, @Param("total") Double total, @Param("numerohabitacion") Long numerohabitacion);

    @Query(value = "select * from reservas where numero_habitacion = :id and date(fecha_reserva) = :fecha", nativeQuery = true)
    Reservas buscarReserva(@Param("id")Long id, @Param("fecha") LocalDate fecha);

    @Transactional
    @Modifying
    @Query(value = "delete from reservas where cod_reserva = :codigo", nativeQuery = true)
    void cancelarReserva(@Param("codigo") String codReserva);

    @Transactional
    @Modifying
    @Query(value = "update reservas set total = :total where cod_reserva = :codigo", nativeQuery = true)
    void pagarReserva(@Param("codigo") String codReserva, @Param("total") Double total);
    Reservas findByCodReserva(String codReserva);
}
