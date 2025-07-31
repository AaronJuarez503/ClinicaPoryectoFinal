package com.CitasClinicas.demo.Modelos;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fechaNacimiento;
    private String direccion;

    @OneToMany(mappedBy = "paciente")
    private List<ExpedienteClinico> expedientesClinicos;

    @OneToMany(mappedBy = "paciente")
    private List<EntradaHospital> entradasHospital;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<ExpedienteClinico> getExpedientesClinicos() {
        return expedientesClinicos;
    }

    public void setExpedientesClinicos(List<ExpedienteClinico> expedientesClinicos) {
        this.expedientesClinicos = expedientesClinicos;
    }

    public List<EntradaHospital> getEntradasHospital() {
        return entradasHospital;
    }

    public void setEntradasHospital(List<EntradaHospital> entradasHospital) {
        this.entradasHospital = entradasHospital;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
