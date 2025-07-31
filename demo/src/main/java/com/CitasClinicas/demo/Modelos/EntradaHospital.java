package com.CitasClinicas.demo.Modelos;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "entradas_hospital")
public class EntradaHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fecha;
    private String tipoEntrada;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteClinico expedienteClinico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ExpedienteClinico getExpedienteClinico() {
        return expedienteClinico;
    }

    public void setExpedienteClinico(ExpedienteClinico expedienteClinico) {
        this.expedienteClinico = expedienteClinico;
    }
}
