package com.reservaHoteles.hoteles.controllers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(value="---", description = "Esta es la comunicación entre el controlador de la sala y todos los servicios de habitacion asociados.")
public class HabitacionController {
    @Autowired
    private ServicioHabitacion serviciohabitacion;
}
