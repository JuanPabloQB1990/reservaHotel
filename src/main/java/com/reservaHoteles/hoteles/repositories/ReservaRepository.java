package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, String> {


    @Query(value = "select * from reservas where numero_habitacion = :id and date(fecha_reserva) = :fecha", nativeQuery = true)
    Reserva buscarReserva(@Param("id")Long id, @Param("fecha") LocalDate fecha);

    Reserva findByCodReserva(String codReserva);
}
