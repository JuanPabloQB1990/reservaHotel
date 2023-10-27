package com.reservaHoteles.hoteles.models;

import com.reservaHoteles.hoteles.models.Cliente;
import jakarta.persistence.*;

@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int direccionId;

    @Column
    private String ciudad;

    @Column
    private String direccion;

    @OneToOne
    @JoinColumn(name = "cliente_cedula")
    private Cliente cliente;

    public Direccion() {
    }

    public Direccion(int direccionId, String ciudad, String direccion, Cliente cliente) {
        this.direccionId = direccionId;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.cliente = cliente;
    }


    public int getDireccionId() {
            return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
