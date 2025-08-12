package com.CitasClinicas.demo.Modelos;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clinicas")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    
    private String direccion;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "clinica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;

    // Se cambia la relación @ManyToMany por @OneToMany
    // que apunta a la nueva entidad de unión ClinicaMedico
    @OneToMany(mappedBy = "clinica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicaMedico> medicos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // El getter y setter para 'medicos' ahora manejan una lista de ClinicaMedico
    public List<ClinicaMedico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<ClinicaMedico> medicos) {
        this.medicos = medicos;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}