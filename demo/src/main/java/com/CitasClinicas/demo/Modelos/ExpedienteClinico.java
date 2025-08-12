package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expedientes_clinicos")
public class ExpedienteClinico {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false)
@Temporal(TemporalType.TIMESTAMP)
private Date fechaCreacion;

@ManyToMany
@JoinTable(
name = "expediente_entrada", // Nombre de la tabla de unión más simple
joinColumns = @JoinColumn(name = "expediente_id"),
inverseJoinColumns = @JoinColumn(name = "entrada_id"),
uniqueConstraints = @UniqueConstraint(columnNames = {"expediente_id", "entrada_id"})
)
private List<EntradaHistorial> entradas = new ArrayList<>();

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "paciente_id", nullable = false)
private Paciente paciente;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "medico_id", nullable = false)
private Medico medico;

@Column(name = "activo")
private boolean activo = true;
 
@Column(name = "notas", length = 1000)
 private String notas;

public ExpedienteClinico() {
this.fechaCreacion = new Date();
this.activo = true;
}

// Getters y Setters
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
public List<EntradaHistorial> getEntradas() {
return entradas;
}

public void setEntradas(List<EntradaHistorial> entradas) {
this.entradas = entradas;
}

 public void agregarEntrada(EntradaHistorial entrada) {
 this.entradas.add(entrada);
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

 public boolean isActivo() {
 return activo;
}

public void setActivo(boolean activo) {
 this.activo = activo;
 }

 public String getNotas() {
return notas;
}

 public void setNotas(String notas) {
this.notas = notas;
 }
}