package com.CitasClinicas.demo.Modelos;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "expedientes_clinicos")
public class ExpedienteClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fechaCreacion;
    
    @OneToMany(mappedBy = "expedienteClinico")
    private List<EntradaHospital> entradasHospital;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<EntradaHospital> getEntradasHospital() {
        return entradasHospital;
    }

    public void setEntradasHospital(List<EntradaHospital> entradasHospital) {
        this.entradasHospital = entradasHospital;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
