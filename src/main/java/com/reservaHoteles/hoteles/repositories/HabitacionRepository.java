package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
