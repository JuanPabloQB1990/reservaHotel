package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.models.Direccion;
import com.reservaHoteles.hoteles.services.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DireccionController {

    private DireccionService direccionService;

    @Autowired
    public DireccionController(DireccionService direccionService){
        this.direccionService = direccionService;
    }

    // ---------------- GET ALL---------------

    @GetMapping("/direcciones")
    public List<Direccion> obtenerDirecciones(){
        return this.direccionService.mostrarDirecciones();
    }
    // ---------------- POST ---------------

    @PostMapping("/direccion")
    public ResponseEntity<Direccion> registrarDireccion(@RequestBody Direccion direccion){
        this.direccionService.crearDireccion(direccion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ---------------- UPDATE BY ID ---------------

    @PutMapping(value = "/actualizar")
    public ResponseEntity<?> actualizarDireccion(@RequestBody Direccion direccion) throws HandlerResponseException {
        direccionService.actualizarDireccion(direccion);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
