package com.reservaHoteles.hoteles;

import com.reservaHoteles.hoteles.excepciones.GlobalExceptionHandler;
import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
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

        if (cliente.getCedula() != null) {
            Optional<Cliente> clienteOptional = this.clienteRepository.findById(cliente.getCedula());
            if (clienteOptional.isPresent()) {
                throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"la cedula no está en la base de datos.");
            }
        }
        if (cliente.getNombre() != null && cliente.getApellido() != null && cliente.getCedula() != null) {
            this.clienteRepository.save(cliente);
            return cliente;
        } else {
            throw new HandlerResponseException( HttpStatus.INTERNAL_SERVER_ERROR ,"la cedula,el nombre y apellido son obligatorios");
        }
    }

    //---------------- READ ----------------
    public List<Cliente> mostrarClientes() throws HandlerResponseException{
        List<Cliente> listaClientes = (List<Cliente>) clienteRepository.findAll();
        if(listaClientes.isEmpty()){
            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"la base de datos está vacía.");
        }
        return listaClientes;
    }
    //---------------- READ BY ID (CEDULA) ----------------
    public Optional<Cliente> buscarClientePorCedula(Long cedula){
        Optional<Cliente> cliente = clienteRepository.findById(cedula);
        if(cliente.isEmpty()){

            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"no se encontró el clietne en la base de datos.");
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

        cliente.get().setEdad(Objects.isNull(clienteAActualizar.getEdad()) ?
                cliente.get().getEdad(): clienteAActualizar.getEdad());

        cliente.get().setDireccion(Objects.isNull(clienteAActualizar.getDireccion()) ?
                cliente.get().getDireccion(): clienteAActualizar.getDireccion());

        cliente.get().setCorreoElectronico(Objects.isNull(clienteAActualizar.getCorreoElectronico()) ?
                cliente.get().getCorreoElectronico(): clienteAActualizar.getCorreoElectronico());
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
