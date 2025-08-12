package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entradas_historial")
public class EntradaHistorial {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String tipoEntrada;

private String diagnostico;
private String notas;
@Temporal(TemporalType.TIMESTAMP)
private Date fecha;

private String tratamiento; // Se añade este campo para la última consulta
 @ManyToOne
@JoinColumn(name = "medico_id")
private Medico medico;

@ManyToMany(mappedBy = "entradas")
private List<ExpedienteClinico> expedientes;

// Getters y Setters
public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

 public String getDiagnostico() {
return diagnostico;
}
 public void setDiagnostico(String diagnostico) {
this.diagnostico = diagnostico;
}

public String getTipoEntrada() {
return tipoEntrada;
}

public void setTipoEntrada(String tipoEntrada) {
this.tipoEntrada = tipoEntrada;
}

public String getNotas() {
return notas;
}

public void setNotas(String notas) {
this.notas = notas;
}

public Date getFecha() {
return fecha;
}

public void setFecha(Date fecha) {
this.fecha = fecha;
}

public Medico getMedico() {
return medico;
}

public void setMedico(Medico medico) {
this.medico = medico;
}

public List<ExpedienteClinico> getExpedientes() {
return expedientes;
}

public void setExpedientes(List<ExpedienteClinico> expedientes) {
this.expedientes = expedientes;
}

public String getTratamiento() {
return tratamiento;
}

public void setTratamiento(String tratamiento) {
this.tratamiento = tratamiento;
}
}