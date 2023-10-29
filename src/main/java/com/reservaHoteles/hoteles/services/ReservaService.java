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
import java.time.temporal.ChronoUnit;

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

        Habitacion habitacionRelacionada = this.habitacionRepository.findBynumerohabitacion(reserva.getHabitacion().getNumerohabitacion());

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
        this.reservaRepository.cancelarReserva(codReserva);
        return "reserva cancelada satisfactoriamente";
    }

    public ReservaConfirmation obtenerTotal(String codigo, LocalDate fechaSalida) {

        Reservas reservaEncontrada = this.reservaRepository.findByCodReserva(codigo);

        Habitacion habitacionRelacionada = this.habitacionRepository.findBynumerohabitacion(reservaEncontrada.getHabitacion().getNumerohabitacion());

        long cantDias = ChronoUnit.DAYS.between(reservaEncontrada.getFechaReserva().toLocalDate(), fechaSalida);

        double totalAPagar = habitacionRelacionada.getPrecioBase() * cantDias;
        reservaEncontrada.setTotal(totalAPagar);

        if (cantDias > 15){
            totalAPagar = habitacionRelacionada.getPrecioBase() - (habitacionRelacionada.getPrecioBase() * 20)/100;
            reservaEncontrada.setTotal(totalAPagar);
        }

        if (habitacionRelacionada.getTipoHabitacion().equals("premium")){
            totalAPagar = totalAPagar - (totalAPagar * 5)/100;
            reservaEncontrada.setTotal(totalAPagar);
        }

        this.reservaRepository.pagarReserva(reservaEncontrada.getCodReserva(), reservaEncontrada.getTotal());

        return new ReservaConfirmation(
                reservaEncontrada.getCodReserva(),
                reservaEncontrada.getFechaReserva(),
                reservaEncontrada.getTotal(),
                habitacionRelacionada.getNumerohabitacion(),
                habitacionRelacionada.getTipoHabitacion(),
                habitacionRelacionada.getPrecioBase());

    }
}
