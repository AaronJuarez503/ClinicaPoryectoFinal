package com.CitasClinicas.demo.Repositorios;

import com.CitasClinicas.demo.Modelos.ExpedienteEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;

public interface IExpedienteEntradaRepositorio extends JpaRepository<ExpedienteEntrada, Long> {
    
    @Query("SELECT e FROM ExpedienteEntrada e WHERE e.expedienteClinico.id = :expedienteId ORDER BY e.fecha DESC")
    List<ExpedienteEntrada> buscarEntradasPorExpediente(@Param("expedienteId") Long expedienteId);
    
    @Query("SELECT e FROM ExpedienteEntrada e WHERE e.expedienteClinico.paciente.id = :pacienteId AND e.fecha BETWEEN :inicio AND :fin")
    List<ExpedienteEntrada> buscarEntradasPorPacienteYRangoFecha(
        @Param("pacienteId") Long pacienteId,
        @Param("inicio") Date inicio,
        @Param("fin") Date fin
    );
    
    @Query("SELECT e FROM ExpedienteEntrada e WHERE e.diagnostico LIKE %:diagnostico%")
    List<ExpedienteEntrada> buscarEntradasPorDiagnostico(@Param("diagnostico") String diagnostico);
    
    @Query("SELECT e FROM ExpedienteEntrada e WHERE e.medico.id = :medicoId AND e.fecha >= :fecha")
    List<ExpedienteEntrada> buscarEntradasPorMedicoYFecha(
        @Param("medicoId") Long medicoId,
        @Param("fecha") Date fecha
    );
    
    @Query("SELECT e.diagnostico, COUNT(e) FROM ExpedienteEntrada e GROUP BY e.diagnostico")
    List<Object[]> contarEntradasPorDiagnostico();
}
