package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.controllers.HabitacionController;
import com.reservaHoteles.hoteles.models.habitacion;
import org.springframework.data.repository.CrudRepository;

public interface HabitacionRepository extends CrudRepository<HabitacionController, Integer> {
}
