package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.repositories.HabitacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicioHabitacion {

    private ServicioHabitacion serviciohabitacion;

    public ServicioHabitacion(HabitacionRepository HabitacionRepository) {
        this.serviciohabitacion = (ServicioHabitacion) HabitacionRepository;
    }
}
