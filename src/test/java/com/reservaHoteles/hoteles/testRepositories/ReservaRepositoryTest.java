package com.reservaHoteles.hoteles.testRepositories;

import com.reservaHoteles.hoteles.models.Cliente;
import com.reservaHoteles.hoteles.models.Habitacion;
import com.reservaHoteles.hoteles.models.Reserva;
import com.reservaHoteles.hoteles.repositories.ReservaRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaRepositoryTest {

    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaRepositoryTest(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Test
    @DisplayName("test crear reserva")
    public void testCrearReserva(){
        Habitacion habitacionTest = new Habitacion(201L, "premium", 1000.0);
        Cliente clienteTest = new Cliente(3332223L,"Juan","Quintero","Medellin","cll 65 aa",33, "juan@gmail.com");
        Reserva reservaTest = new Reserva("001", LocalDateTime.of(2023,10,29,19,30,00), clienteTest, 0, habitacionTest);

        Reserva reservaCreada = this.reservaRepository.save(reservaTest);

        assertEquals(reservaCreada.getCodReserva(),reservaTest.getCodReserva());

    }

    @Test
    @DisplayName("test buscar reserva")
    public void testBuscarReserva(){
        Habitacion habitacionTest = new Habitacion(201L, "premium", 1000.0);
        Cliente clienteTest = new Cliente(3332223L,"Juan","Quintero","Medellin","cll 65 aa",33, "juan@gmail.com");
        Reserva reservaTest = new Reserva("001", LocalDateTime.of(2023,10,29,19,30,00), clienteTest, 0, habitacionTest);

        Reserva reservaCreada = this.reservaRepository.save(reservaTest);

        Reserva reservaEncontrada = this.reservaRepository.findByCodReserva("001");

        assertEquals(reservaCreada.getCodReserva(),reservaEncontrada.getCodReserva());


    }
}
