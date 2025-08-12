package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entrada_historial") // Nombre de la tabla corregido
public class EntradaHistorial { // Nombre de la clase corregido
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fecha;
    private String tipoEntrada;
    private String descripcion;
    private String detallesAdicionales;

    @ManyToOne
    @JoinColumn(name = "idProfesionalResponsable")
    private Usuario profesionalResponsable;
    
    @ManyToMany(mappedBy = "entradas")
    private List<ExpedienteClinico> expedientes;
    
    // Getters y Setters
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

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }

    public Usuario getProfesionalResponsable() {
        return profesionalResponsable;
    }

    public void setProfesionalResponsable(Usuario profesionalResponsable) {
        this.profesionalResponsable = profesionalResponsable;
    }

    public List<ExpedienteClinico> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(List<ExpedienteClinico> expedientes) {
        this.expedientes = expedientes;
    }
}