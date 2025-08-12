package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico extends Usuario {
    
    private String especialidad;
    private String licenciaMedica;
    private String experiencia;
    
    // Propiedad 'activo' agregada para solucionar el error de mapeo.
    private boolean activo = true;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CupoDeAtencion> cuposDeAtencion = new ArrayList<>();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicaMedico> clinicas = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public Medico() {
        super();
    }
    
    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getLicenciaMedica() {
        return licenciaMedica;
    }

    public void setLicenciaMedica(String licenciaMedica) {
        this.licenciaMedica = licenciaMedica;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    
    // Getter y Setter para la propiedad 'activo'
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<CupoDeAtencion> getCuposDeAtencion() {
        return cuposDeAtencion;
    }

    public void setCuposDeAtencion(List<CupoDeAtencion> cuposDeAtencion) {
        this.cuposDeAtencion = cuposDeAtencion;
    }

    public List<ClinicaMedico> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<ClinicaMedico> clinicas) {
        this.clinicas = clinicas;
    }
}