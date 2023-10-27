package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.ReservaFindException;
import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.models.ReservaConfirmation;
import com.reservaHoteles.hoteles.models.Reservas;
import com.reservaHoteles.hoteles.repositories.ClienteRepository;
import com.reservaHoteles.hoteles.repositories.HabitacionRepository;
import com.reservaHoteles.hoteles.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;

    private HabitacionRepository habitacionRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository, HabitacionRepository habitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public ReservaConfirmation crearReserva(Reservas reserva) {

        Cliente clienteEncontrado = this.clienteRepository.findByCedula(reserva.getCliente().getCedula());

        LocalDateTime fecha = reserva.getFechaReserva();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaReserva = LocalDate.parse(fecha.format(myFormatObj));

        Reservas reservaEncontrada = this.reservaRepository.buscarReserva(reserva.getHabitacion().getNumerohabitacion(),fechaReserva);


        if (clienteEncontrado != null){
            if (reservaEncontrada != null){
                throw new ReservaFindException("la habitacion ya se encuentra disponible para esta fecha");
            }

            this.reservaRepository.crearReserva(
                    reserva.getCodReserva(),
                    reserva.getFechaReserva(),
                    reserva.getCliente().getCedula(),
                    reserva.getTotal(),
                    reserva.getHabitacion().getNumerohabitacion());
        }

        this.reservaRepository.crearReserva(
                reserva.getCodReserva(),
                reserva.getFechaReserva(),
                reserva.getCliente().getCedula(),
                reserva.getTotal(),
                reserva.getHabitacion().getNumerohabitacion());


        Habitacion habitacionRelacionada = this.habitacionRepository.findByNumero(reserva.getHabitacion().getNumerohabitacion());

        return new ReservaConfirmation(
                reserva.getCodReserva(),
                reserva.getFechaReserva(),
                reserva.getTotal(),
                habitacionRelacionada.getNumerohabitacion(),
                habitacionRelacionada.getTipoHabitacion(),
                habitacionRelacionada.getPrecioBase());
    }

    public Cliente obtenerReservasPorCliente(Long cedula) {
        return this.clienteRepository.findByCedula(cedula);

    }

    public String cancelarReserva(String codReserva) {

        this.reservaRepository.deleteByCodReserva(codReserva);
        return "reserva cancelada satisfactoriamente";
    }

}
