package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ExpedienteEntradas")
public class ExpedienteEntrada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expediente_id", nullable = false)
    private ExpedienteClinico expedienteClinico;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(length = 200, nullable = false)
    private String diagnostico;

    @Column(length = 500)
    private String tratamiento;

    @Column(length = 1000)
    private String observaciones;

    @Column(length = 200)
    private String resultadosExamenes;

    @Column(name = "signos_vitales", length = 200)
    private String signosVitales;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "presion_arterial", length = 20)
    private String presionArterial;

    // Constructores
    public ExpedienteEntrada() {
    }

    public ExpedienteEntrada(ExpedienteClinico expedienteClinico, Medico medico, String diagnostico) {
        this.expedienteClinico = expedienteClinico;
        this.medico = medico;
        this.diagnostico = diagnostico;
        this.fecha = new Date();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExpedienteClinico getExpedienteClinico() {
        return expedienteClinico;
    }

    public void setExpedienteClinico(ExpedienteClinico expedienteClinico) {
        this.expedienteClinico = expedienteClinico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getResultadosExamenes() {
        return resultadosExamenes;
    }

    public void setResultadosExamenes(String resultadosExamenes) {
        this.resultadosExamenes = resultadosExamenes;
    }

    public String getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(String signosVitales) {
        this.signosVitales = signosVitales;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }
}
