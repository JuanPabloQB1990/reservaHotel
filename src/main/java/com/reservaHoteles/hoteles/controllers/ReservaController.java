package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.ReservaConfirmationList;
import com.reservaHoteles.hoteles.models.Reservas;
import com.reservaHoteles.hoteles.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/reservas")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ReservaConfirmationList crearReserva(@RequestBody @Valid Reservas reserva){
        return this.reservaService.crearReserva(reserva);
    }

    @GetMapping(path = "cliente/{cedula}")
    public Cliente obtenerReservasPorCliente(@PathVariable Long cedula){
        return this.reservaService.obtenerReservasPorCliente(cedula);
    }
}
