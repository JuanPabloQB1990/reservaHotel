package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.models.Direccion;
import com.reservaHoteles.hoteles.repositories.DireccionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class DireccionService {
    DireccionRepository direccionRepository;

    // ---------------- CREATE ---------------
    public  Direccion crearDireccion(Direccion direccion) throws HandlerResponseException {

            Direccion dirrecionCreada = new Direccion(direccion.getDireccionId(), direccion.getCiudad(), direccion.getDireccion(), direccion.getCliente());
            this.direccionRepository.save(dirrecionCreada);
            return dirrecionCreada;
    }

    //---------------- READ ----------------

    public List<Direccion> mostrarDirecciones() throws HandlerResponseException {
        List<Direccion> listaDirecciones = (List<Direccion>) direccionRepository.findAll();
        if(listaDirecciones.isEmpty()){
            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"la base de datos está vacía.");
        }
        return listaDirecciones;
    }

    // ----------------UPDATE----------------

    public Direccion actualizarDireccion(Direccion direAActualizar) throws HandlerResponseException {

        Optional<Direccion> direccion = direccionRepository.findById(direAActualizar.getDireccionId());

        direccion.get().setDireccion(Objects.isNull(direAActualizar.getDireccion()) ?
                direccion.get().getDireccion(): direAActualizar.getDireccion());

        direccion.get().setCiudad(Objects.isNull(direAActualizar.getCiudad()) ?
                direccion.get().getCiudad(): direAActualizar.getCiudad());

        direccionRepository.save(direccion.get());

        return direccionRepository.save(direAActualizar);
    }


}
