package com.reservaHoteles.hoteles.services;

import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.excepciones.ReservaFindException;
import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.models.ReservaConfirmation;
import com.reservaHoteles.hoteles.models.Reserva;
import com.reservaHoteles.hoteles.repositories.ClienteRepository;
import com.reservaHoteles.hoteles.repositories.HabitacionRepository;
import com.reservaHoteles.hoteles.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ReservaConfirmation crearReserva(Reserva reserva) {

        Cliente clienteEncontrado = this.clienteRepository.findByCedula(reserva.getCliente().getCedula());

        LocalDateTime fecha = reserva.getFechaReserva();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaReserva = LocalDate.parse(fecha.format(myFormatObj));

        Reserva reservaEncontrada = this.reservaRepository.buscarReserva(reserva.getHabitacion().getNumerohabitacion(),fechaReserva);

        if (clienteEncontrado != null){
            if (reservaEncontrada != null){
                throw new HandlerResponseException(HttpStatus.NOT_FOUND, "la habitacion no se encuentra disponible para esta fecha");
            }
            this.reservaRepository.save(reserva);

        }else{
            throw new HandlerResponseException(HttpStatus.NOT_FOUND, "el usuario no se encuentra registrado");

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
        this.reservaRepository.deleteById(codReserva);
        return "reserva cancelada satisfactoriamente";
    }

    public ReservaConfirmation obtenerTotal(String codigo, LocalDate fechaSalida) {

        if (codigo == null && fechaSalida == null){
            throw new NullPointerException();
        }

        Reserva reservaEncontrada = this.reservaRepository.findByCodReserva(codigo);

        Habitacion habitacionRelacionada = this.habitacionRepository.findBynumerohabitacion(reservaEncontrada.getHabitacion().getNumerohabitacion());

        long cantDias = ChronoUnit.DAYS.between(reservaEncontrada.getFechaReserva().toLocalDate(), fechaSalida);

        double totalAPagar = habitacionRelacionada.getPrecioBase() * cantDias;
        reservaEncontrada.setTotal(totalAPagar);

        if (cantDias > 15){
            totalAPagar = totalAPagar - (habitacionRelacionada.getPrecioBase() * 20)/100;
            reservaEncontrada.setTotal(totalAPagar);
        }

        if (habitacionRelacionada.getTipoHabitacion().equals("premium")){
            totalAPagar = totalAPagar - (totalAPagar * 5)/100;
            reservaEncontrada.setTotal(totalAPagar);
        }
        Reserva reservaPagada = this.reservaRepository.save(reservaEncontrada);

        return new ReservaConfirmation(
                reservaPagada.getCodReserva(),
                reservaPagada.getFechaReserva(),
                reservaPagada.getTotal(),
                habitacionRelacionada.getNumerohabitacion(),
                habitacionRelacionada.getTipoHabitacion(),
                habitacionRelacionada.getPrecioBase());

    }
}
