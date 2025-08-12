package com.CitasClinicas.demo.Modelos;

import java.util.Date;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fechaHora;
    private String estado;
    private String motivo;
    private String notaAtencion;
    private BigDecimal costo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cupo_atencion_id")
    private CupoDeAtencion cupoDeAtencion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotaAtencion() {
        return notaAtencion;
    }

    public void setNotaAtencion(String notaAtencion) {
        this.notaAtencion = notaAtencion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public CupoDeAtencion getCupoDeAtencion() {
        return cupoDeAtencion;
    }

    public void setCupoDeAtencion(CupoDeAtencion cupoDeAtencion) {
        this.cupoDeAtencion = cupoDeAtencion;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    // --- Getters y setters para el nuevo campo 'medico' ---

    // ----------------------------------------------------
}