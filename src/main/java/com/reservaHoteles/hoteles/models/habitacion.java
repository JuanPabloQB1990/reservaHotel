package com.reservaHoteles.hoteles.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

import static org.hibernate.annotations.CascadeType.*;

public class habitacion {

    @ApiModelProperty(value = "Habitacion id", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroHabitacion;

    @ApiModelProperty(value = "Tipo de habitacion", example ="basica")
    @Column(name = "Tipo de habitacion")
    private String tipoHabitacion;

    @ApiModelProperty(value = "Precio de habitacion", example ="100")
    @Column(name = "Precio")
    private Double Precio;

    @OneToMany(cascade = {PERSIST}, mappedBy = "Habitacion")
    @JsonIgnoreProperties("Habitacion")
    /*
    private List<reservas> reservations;
     */
    public habitacion(){}

    public habitacion(Integer numberRoom, String roomType, Double price) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.Precio = Precio;
    }
    public Integer numeroHabitacion() {
        return numeroHabitacion;
    }

    public void setnumeroHabitacion(Integer numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String gettipoHabitacion() {
        return tipoHabitacion;
    }

    public void setnumeroHabitacion(String setnumeroHabitacion) {
        this.numeroHabitacion = Integer.valueOf(setnumeroHabitacion);
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public List<reservas> getReservations() {
        return Reservas;
    }

    public void setreservas(List<Reservas> reservas) {
        this.Reservas = reservas;
    }

 
}
