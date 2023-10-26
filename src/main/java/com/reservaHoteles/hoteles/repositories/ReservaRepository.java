package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reservas, String> {

    List<Reservas> findByIdCliente(Integer id);

    @Query(value = "select * from reservas where id_habitacion = :id and date(fecha_reserva) = :fecha", nativeQuery = true)
    Reservas buscarReserva(@Param("id")Integer id, @Param("fecha") String fecha);
}
