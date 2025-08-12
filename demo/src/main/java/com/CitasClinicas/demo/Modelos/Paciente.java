package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Paciente extends Usuario {
    private Date fechaNacimiento;
    private String direccion;

    // Lado "inverso" de la relación, lo que significa que el otro lado
    // (ExpedienteClinico) es el propietario y tiene la clave foránea.
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExpedienteClinico expedienteClinico;

    // Relación con Cita.
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;

    // Getters y Setters
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ExpedienteClinico getExpedienteClinico() {
        return expedienteClinico;
    }

    public void setExpedienteClinico(ExpedienteClinico expedienteClinico) {
        this.expedienteClinico = expedienteClinico;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}