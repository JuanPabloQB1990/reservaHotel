package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.services.HabitacionService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
/*
    @GetMapping
    public List<Habitacion> obtenerHabitacionesDisponibles(@RequestParam @Nullable LocalDate fechaRequerida, @RequestParam @Nullable String tipoHabitacion){
        if (tipoHabitacion == null && fechaRequerida == null){
            return this.habitacionService.obtenerHabitacionesDisponibles();
        }else{
            return this.habitacionService.obtenerHabitacionesPorFechaYTipo(fechaRequerida, tipoHabitacion);
        }
    }
    */
}
