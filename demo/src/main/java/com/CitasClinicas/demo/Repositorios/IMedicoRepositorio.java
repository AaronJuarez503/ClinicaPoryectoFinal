package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepositorio extends JpaRepository<Medico, Long> {

}
