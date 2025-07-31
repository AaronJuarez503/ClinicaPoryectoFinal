package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
