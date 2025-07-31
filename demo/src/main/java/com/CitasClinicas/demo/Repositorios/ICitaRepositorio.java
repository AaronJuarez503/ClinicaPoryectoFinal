package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitaRepositorio extends JpaRepository<Cita, Long> {

}
