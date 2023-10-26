package com.reservaHoteles.hoteles;

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
