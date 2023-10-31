package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    Habitacion findBynumerohabitacion(Long numeroHabitacion);

    @Query(value = "select h.* from habitacion as h inner join reservas as r on h.numerohabitacion != r.numero_habitacion", nativeQuery = true)
    List<Habitacion> buscarHabitacionesDisponible();
}
