package com.CitasClinicas.demo.Repositorios;

import com.CitasClinicas.demo.Modelos.CupoDeAtencion;
//import com.CitasClinicas.demo.Modelos.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface ICupoDeAtencionRepositorio extends JpaRepository<CupoDeAtencion, Long> {
    
    @Query("SELECT c FROM CupoDeAtencion c WHERE c.medico.id = :medicoId AND c.fecha >= CURRENT_DATE AND c.estado = 'Disponible'")
    List<CupoDeAtencion> buscarCuposDisponiblesPorMedico(@Param("medicoId") Long medicoId);
    
    @Query("SELECT c FROM CupoDeAtencion c WHERE c.fecha = :fecha")
    List<CupoDeAtencion> buscarCuposPorFecha(@Param("fecha") Date fecha);
    
    @Query("SELECT c FROM CupoDeAtencion c WHERE c.medico.especialidad = :especialidad AND c.fecha >= :fecha AND c.estado = 'Disponible'")
    List<CupoDeAtencion> buscarCuposDisponiblesPorEspecialidad(
        @Param("especialidad") String especialidad,
        @Param("fecha") Date fecha
    );
    
    @Query("SELECT c FROM CupoDeAtencion c WHERE c.costo <= :costoMaximo AND c.estado = 'Disponible'")
    List<CupoDeAtencion> buscarCuposPorCostoMaximo(@Param("costoMaximo") Double costoMaximo);
    
    @Query("SELECT c FROM CupoDeAtencion c WHERE c.fecha BETWEEN :inicio AND :fin AND c.estado = 'Disponible'")
    List<CupoDeAtencion> buscarCuposDisponiblesEnRango(
        @Param("inicio") Date inicio,
        @Param("fin") Date fin
    );
}
    