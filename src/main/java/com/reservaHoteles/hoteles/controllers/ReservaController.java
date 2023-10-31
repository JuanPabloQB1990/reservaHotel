package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.ReservaConfirmation;
import com.reservaHoteles.hoteles.models.Reserva;
import com.reservaHoteles.hoteles.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/reservas")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ReservaConfirmation crearReserva(@RequestBody @Valid Reserva reserva){
        return this.reservaService.crearReserva(reserva);
    }

    @GetMapping(path = "cliente/{cedula}")
    public Cliente obtenerReservasPorCliente(@PathVariable Long cedula){
        return this.reservaService.obtenerReservasPorCliente(cedula);
    }

    @DeleteMapping(path = "{codigo}")
    public String cancelarReserva(@PathVariable String codigo){
        return this.reservaService.cancelarReserva(codigo);
    }

    @GetMapping
    public ReservaConfirmation obtenerTotal(@RequestParam(name = "codigo") String codigo, @RequestParam(name = "fechaSalida") LocalDate fechaSalida){
        return this.reservaService.obtenerTotal(codigo, fechaSalida);
    }
}
