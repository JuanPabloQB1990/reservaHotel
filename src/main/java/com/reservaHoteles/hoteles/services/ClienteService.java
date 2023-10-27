package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    // ---------------- CREATE ---------------
    public Cliente registrarCliente(Cliente cliente) throws HandlerResponseException {

        Cliente clienteOptional = this.clienteRepository.findByCedula(cliente.getCedula());
            if (clienteOptional != null) {
                throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"el cliente ya esta registrado");
            }

        Cliente clienteCreado = this.clienteRepository.save(cliente);
        return clienteCreado;

    }

    //---------------- READ ----------------
    public List<Cliente> mostrarClientes() throws HandlerResponseException{
        List<Cliente> listaClientes = (List<Cliente>) clienteRepository.findAll();
        if(listaClientes.isEmpty()){
            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"no hay usuarios registrados.");
        }
        return listaClientes;
    }
    //---------------- READ BY ID (CEDULA) ----------------
    public Cliente buscarClientePorCedula(Long cedula){
        Cliente cliente = clienteRepository.findByCedula(cedula);
        if(cliente == null){

            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"no se encontró el cliente en la base de datos.");
        }
        return cliente;
    }

    // ----------------UPDATE----------------

    public Cliente actualizarCliente(Cliente clienteAActualizar) throws HandlerResponseException {

        Optional<Cliente> cliente = clienteRepository.findById(clienteAActualizar.getCedula());

        cliente.get().setNombre(Objects.isNull(clienteAActualizar.getNombre()) ?
            cliente.get().getNombre(): clienteAActualizar.getNombre());

        cliente.get().setApellido(Objects.isNull(clienteAActualizar.getApellido()) ?
            cliente.get().getApellido(): clienteAActualizar.getApellido());

        cliente.get().setEdad(clienteAActualizar.getEdad());

        cliente.get().setDireccion(Objects.isNull(clienteAActualizar.getDireccion()) ?
                cliente.get().getDireccion(): clienteAActualizar.getDireccion());

        cliente.get().setCorreo(Objects.isNull(clienteAActualizar.getCorreo()) ?
                cliente.get().getCorreo(): clienteAActualizar.getCorreo());
        clienteRepository.save(cliente.get());

        return clienteRepository.save(clienteAActualizar);
    }

    //---------------- DELETE----------------

    public String borrarCliente(Long clientNumberId) throws HandlerResponseException {
        if (clienteRepository.findById(clientNumberId).isPresent()){
            clienteRepository.deleteById(clientNumberId);
            return "Cliente eliminado exitosamente";
        }else  {
            throw new HandlerResponseException(HttpStatus.ACCEPTED, "No existe o no fue posible eliminar el cliente, por favor revise los datos ingresados e intente nuevamente");
        }
    }
}
