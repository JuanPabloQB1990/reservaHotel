package com.reservaHoteles.hoteles.testServices;

import com.reservaHoteles.hoteles.excepciones.HandlerResponseException;
import com.reservaHoteles.hoteles.excepciones.ReservaFindException;
import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.models.Reserva;
import com.reservaHoteles.hoteles.models.ReservaConfirmation;
import com.reservaHoteles.hoteles.repositories.ClienteRepository;
import com.reservaHoteles.hoteles.repositories.HabitacionRepository;
import com.reservaHoteles.hoteles.repositories.ReservaRepository;
import com.reservaHoteles.hoteles.services.ReservaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {


    private ReservaService reservaService;

    private ReservaRepository reservaRepository;

    private ClienteRepository clienteRepository;

    private HabitacionRepository habitacionRepository;

    Habitacion habitacionTest;
    Habitacion habitacionTestPremiuim;

    Cliente clienteTest;
    Reserva reservaTest;

    @BeforeEach
    public void getSetup(){
        this.reservaRepository = mock(ReservaRepository.class);
        this.clienteRepository = mock(ClienteRepository.class);
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.reservaService = new ReservaService(this.reservaRepository, this.clienteRepository, this.habitacionRepository);

        habitacionTest = new Habitacion(201L, "standart", 1000.0);
        habitacionTestPremiuim = new Habitacion(201L, "premium", 1000.0);

        clienteTest = new Cliente(3332223L,"Juan","Quintero","Medellin","cll 65 aa",33, "juan@gmail.com");
        reservaTest = new Reserva("001", LocalDateTime.of(2023,11,01,19,30,00), clienteTest, 0, habitacionTest);

    }

    @Test
    public void testCrearReservaClienteNulo(){

        when(this.clienteRepository.findByCedula(any())).thenReturn(null);
        Assertions.assertThrows(HandlerResponseException.class,
                ()-> this.reservaService.crearReserva(reservaTest));

        verify(reservaRepository, never()).save(reservaTest);

    }

    @Test
    public void testCrearReservaConReservaExistente(){

        when(this.clienteRepository.findByCedula(any())).thenReturn(clienteTest);
        when(this.reservaRepository.buscarReserva(any(),any())).thenReturn(reservaTest);

        HandlerResponseException rfe = Assertions.assertThrows(HandlerResponseException.class,
                ()-> this.reservaService.crearReserva(reservaTest));
        verify(reservaRepository, never()).save(reservaTest);

    }

    @Test
    public void testCrearReservaSatisfactoriamente(){

        when(this.clienteRepository.findByCedula(any())).thenReturn(clienteTest);
        when(this.reservaRepository.buscarReserva(any(),any())).thenReturn(null);
        when(this.habitacionRepository.findBynumerohabitacion(any())).thenReturn(habitacionTest);

        ReservaConfirmation reservaCreada = this.reservaService.crearReserva(reservaTest);

        assertThat(reservaCreada.codReserva).isEqualTo(reservaTest.getCodReserva());
        assertThat(reservaCreada.getNumeroHabitacion()).isEqualTo(habitacionTest.getNumerohabitacion());

        verify(reservaRepository, times(1)).save(reservaTest);

    }

    @Test
    public void testObtenerTotalConFechaNula(){
        //LocalDate fechaSalidaTest = LocalDate.of(2023,10,30);
        LocalDate fechaSalidaTest = null;

        String codigo = "dfsd44fff";
        Assertions.assertThrows(NullPointerException.class,
                () -> this.reservaService.obtenerTotal(codigo,fechaSalidaTest));

        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    public void testObtenerTotalConCodigoNulo(){
        LocalDate fechaSalidaTest = LocalDate.of(2023,10,30);

        String codigo = null;
        Assertions.assertThrows(NullPointerException.class,
                () -> this.reservaService.obtenerTotal(codigo,fechaSalidaTest));

        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    public void testPagarHabitacionStandartMenorA15Dias(){
        when(this.reservaRepository.findByCodReserva(any())).thenReturn(reservaTest);
        when(this.habitacionRepository.findBynumerohabitacion(any())).thenReturn(habitacionTest);
        when(this.reservaRepository.save(any())).thenReturn(reservaTest);
        LocalDate fechaSalidaTest = LocalDate.of(2023,11,10);

        ReservaConfirmation reservaRealizada = this.reservaService.obtenerTotal("1234", fechaSalidaTest);

        assertThat(reservaRealizada.getTotalAPagar()).isEqualTo(9000);


    }

    @Test
    public void testPagarHabitacionStandartMayorA15Dias(){
        when(this.reservaRepository.findByCodReserva(any())).thenReturn(reservaTest);
        when(this.habitacionRepository.findBynumerohabitacion(any())).thenReturn(habitacionTest);
        when(this.reservaRepository.save(any())).thenReturn(reservaTest);
        LocalDate fechaSalidaTest = LocalDate.of(2023,11,20);

        ReservaConfirmation reservaRealizada = this.reservaService.obtenerTotal("1234", fechaSalidaTest);

        assertThat(reservaRealizada.getTotalAPagar()).isEqualTo(18800);
    }

    @Test
    public void testPagarHabitacionPremiumMayorA15Dias(){
        when(this.reservaRepository.findByCodReserva(any())).thenReturn(reservaTest);
        when(this.habitacionRepository.findBynumerohabitacion(any())).thenReturn(habitacionTestPremiuim);
        when(this.reservaRepository.save(any())).thenReturn(reservaTest);
        LocalDate fechaSalidaTest = LocalDate.of(2023,11,20);

        ReservaConfirmation reservaRealizada = this.reservaService.obtenerTotal("1234", fechaSalidaTest);

        assertThat(reservaRealizada.getTotalAPagar()).isEqualTo(17860);


    }

    @Test
    public void testPagarHabitacionPremiumMenorA15Dias(){
        when(this.reservaRepository.findByCodReserva(any())).thenReturn(reservaTest);
        when(this.habitacionRepository.findBynumerohabitacion(any())).thenReturn(habitacionTestPremiuim);
        when(this.reservaRepository.save(any())).thenReturn(reservaTest);
        LocalDate fechaSalidaTest = LocalDate.of(2023,11,05);

        ReservaConfirmation reservaRealizada = this.reservaService.obtenerTotal("1234", fechaSalidaTest);

        assertThat(reservaRealizada.getTotalAPagar()).isEqualTo(3800);


    }
}
