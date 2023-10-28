package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioHabitacion {

    private ServicioHabitacion serviciohabitacion;

    @Autowired
    public ServicioHabitacion(HabitacionRepository HabitacionRepository) {
        this.serviciohabitacion = (ServicioHabitacion) HabitacionRepository;
    }
}
