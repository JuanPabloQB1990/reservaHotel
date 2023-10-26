package com.reservaHoteles.hoteles.models;

import com.reservaHoteles.hoteles.models.Cliente;
import jakarta.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long direccionId;

    @Column
    private String ciudad;

    @Column
    private String direccion;

    @ManyToOne()
    @JoinColumn(name = "cedula", nullable = false)
    private Cliente cliente;
}
