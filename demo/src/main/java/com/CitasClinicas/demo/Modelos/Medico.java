package com.CitasClinicas.demo.Modelos;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico extends Usuario {
    private String especialidad;
    private String licenciaMedica;
    private String experiencia;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    @OneToMany(mappedBy = "medico")
    private List<ExpedienteClinico> expedientesClinicos;

    @ManyToMany(mappedBy = "medicos")
    private List<Clinica> clinicas;

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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<ExpedienteClinico> getExpedientesClinicos() {
        return expedientesClinicos;
    }

    public void setExpedientesClinicos(List<ExpedienteClinico> expedientesClinicos) {
        this.expedientesClinicos = expedientesClinicos;
    }

    public List<Clinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<Clinica> clinicas) {
        this.clinicas = clinicas;
    }
}
