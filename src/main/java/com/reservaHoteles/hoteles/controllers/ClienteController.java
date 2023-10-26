package com.reservaHoteles.hoteles.controllers;

import com.reservaHoteles.hoteles.services.ClienteService;
import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    // ---------------- GET ALL---------------

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        return this.clienteService.mostrarClientes();
    }

    // ---------------- GET BY ID (CEDULA)---------------

    @GetMapping("/cliente/{cedula}")
    public Optional<Cliente> obtenerIdCliente(@PathVariable("cedula") Long cedula){
        return this.clienteService.buscarClientePorCedula(cedula);
    }

    // ---------------- POST ---------------

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente){
        this.clienteService.registrarCliente(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ---------------- DELETE BY ID (CEDULA)---------------

    @DeleteMapping(value = "/borrar/{cedula}")
    public ResponseEntity<?> borrarCliente(@PathVariable Long cedula) throws HandlerResponseException {
        clienteService.borrarCliente(cedula);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // ---------------- UPDATE BY ID (CEDULA)---------------

    @PutMapping(value = "/actualizar")
    public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente) throws HandlerResponseException {
        clienteService.actualizarCliente(cliente);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
