package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.ReservaFindException;
import com.reservaHoteles.hoteles.models.ReservaConfirmationList;
import com.reservaHoteles.hoteles.models.Reservas;
import com.reservaHoteles.hoteles.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ReservaConfirmationList crearReserva(Reservas reserva) {
        String fecha = reserva.getFechaReserva().substring(0,10);
        Reservas reservaEncontrada = this.reservaRepository.buscarReserva(reserva.getIdHabitacion(),fecha);

        if (reservaEncontrada == null){
            Reservas reservaCreada = this.reservaRepository.save(reserva);
            return new ReservaConfirmationList(
                    reservaCreada.getCodReserva(),
                    reservaCreada.getFechaReserva(),
                    reservaCreada.getTotalAPagar(),
                    reservaCreada.getIdHabitacion());
        }

        throw new ReservaFindException("la habitacion ya se encuentra disponible para esta fecha");

    }

    public List<Reservas> obtenerReservasPorCliente(Integer id) {
        return this.reservaRepository.findByIdCliente(id);
    }
}
