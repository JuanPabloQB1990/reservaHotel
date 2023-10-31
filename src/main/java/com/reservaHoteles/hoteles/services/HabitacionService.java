package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.EntitiesNotFoundExcepcion;
import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return this.habitacionRepository.save(habitacion);
    }

    public List<Habitacion> obtenerHabitaciones() {
        return this.habitacionRepository.findAll();
    }

    public List<Habitacion> obtenerHabitacionesDisponibles() {
        List<Habitacion> habitacionesDisponibles = this.habitacionRepository.buscarHabitacionesDisponible();

        if (habitacionesDisponibles.isEmpty()){
            throw new EntitiesNotFoundExcepcion("no hay habitaciones disponibles");
        }

        return habitacionesDisponibles;

    }

    public List<Habitacion> obtenerHabitacionesPorFechaYTipo(LocalDate fechaRequerida, String tipoHabitacion) {
        return null;
    }
}
