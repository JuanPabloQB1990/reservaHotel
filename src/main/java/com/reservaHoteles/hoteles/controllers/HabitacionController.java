package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.models.habitacion;
import com.reservaHoteles.hoteles.services.ServicioHabitacion;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(value="---", description = "Esta es la comunicación entre el controlador de la sala y todos los servicios de habitacion asociados.")
public class HabitacionController {
    @Autowired
    private ServicioHabitacion serviciohabitacion;

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "habitacion creada exitosamente")
    })
    @PostMapping("/habitacion")
    @ResponseStatus(HttpStatus.CREATED)
    public Object register(@ApiParam(value = "habitacion object", required = true) @RequestBody habitacion habitacion){
        return serviciohabitacion ;
    }
}
