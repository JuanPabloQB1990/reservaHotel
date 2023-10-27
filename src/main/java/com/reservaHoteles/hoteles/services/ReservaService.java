package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.ReservaFindException;
import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.ReservaConfirmationList;
import com.reservaHoteles.hoteles.models.Reservas;
import com.reservaHoteles.hoteles.repositories.ClienteRepository;
import com.reservaHoteles.hoteles.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;

    }

    public ReservaConfirmationList crearReserva(Reservas reserva) {

        Cliente clienteEncontrado = this.clienteRepository.findByCedula(reserva.getCliente().getCedula());
        String fecha = reserva.getFechaReserva().substring(0,10);
        Reservas reservaEncontrada = this.reservaRepository.buscarReserva(reserva.getHabitacion().getNumero(),fecha);

        if (clienteEncontrado != null){
            if (reservaEncontrada != null){
                throw new ReservaFindException("la habitacion ya se encuentra disponible para esta fecha");
            }
        }
        this.reservaRepository.crearReserva(
                reserva.getCodReserva(),
                reserva.getFechaReserva(),
                reserva.getCliente().getCedula(),
                reserva.getTotalAPagar(),
                reserva.getHabitacion().getNumero());

        return new ReservaConfirmationList(
                reserva.getCodReserva(),
                reserva.getFechaReserva(),
                reserva.getTotalAPagar(),
                reserva.getHabitacion());
    }


    public Cliente obtenerReservasPorCliente(Long cedula) {


        return this.clienteRepository.findByCedula(cedula);


    }
}
