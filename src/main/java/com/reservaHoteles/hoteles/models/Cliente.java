package com.reservaHoteles.hoteles.models;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "cedula")
    private Long cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    @ManyToOne()
    @JoinColumn(name = "direccionId", nullable = false)
    private Direccion direccion;

    @Column(name = "edad")
    private int edad;

    @Column(name = "correoElectronico")
    private String correoElectronico;

    public Cliente() {
    }


    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
