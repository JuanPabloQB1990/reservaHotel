package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/habitaciones")
public class HabitacionController {

    private HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping
    public Habitacion crearHabitacion(@RequestBody Habitacion habitacion){
        return this.habitacionService.crearHabitacion(habitacion);
    }

    @GetMapping
    public List<Habitacion> obtenerHabitaciones(){
        return this.habitacionService.obtenerHabitaciones();
    }
}
