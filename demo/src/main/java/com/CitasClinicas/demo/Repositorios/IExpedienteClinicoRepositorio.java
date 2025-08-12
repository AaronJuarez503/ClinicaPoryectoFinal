package com.CitasClinicas.demo.Repositorios;

import com.CitasClinicas.demo.Modelos.ExpedienteClinico;
//import com.CitasClinicas.demo.Modelos.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IExpedienteClinicoRepositorio extends JpaRepository<ExpedienteClinico, Long> {

    // Búsquedas básicas con @Query
    @Query("SELECT e FROM ExpedienteClinico e WHERE e.paciente.id = :pacienteId")
    ExpedienteClinico buscarExpedientePorPaciente(@Param("pacienteId") Long pacienteId);

    // Búsquedas por fechas
    @Query("SELECT e FROM ExpedienteClinico e WHERE e.fechaCreacion BETWEEN :fechaInicio AND :fechaFin")
    List<ExpedienteClinico> buscarExpedientesPorRangoFecha(
        @Param("fechaInicio") Date fechaInicio,
        @Param("fechaFin") Date fechaFin
    );

    // Búsquedas avanzadas
    @Query("SELECT e FROM ExpedienteClinico e " +
           "WHERE e.paciente.id = :pacienteId " +
           "AND EXISTS (SELECT eh FROM EntradaHospital eh " +
           "WHERE eh.expedienteClinico = e AND eh.fecha >= :fecha)")
    List<ExpedienteClinico> buscarExpedientesConEntradasRecientes(
        @Param("pacienteId") Long pacienteId,
        @Param("fecha") Date fecha
    );

    // Estadísticas y conteos
    @Query("SELECT COUNT(eh) FROM ExpedienteClinico e JOIN e.entradasHospital eh " +
           "WHERE e.id = :expedienteId AND eh.fecha BETWEEN :inicio AND :fin")
    Long contarEntradasEnPeriodo(
        @Param("expedienteId") Long expedienteId,
        @Param("inicio") Date inicio,
        @Param("fin") Date fin
    );

    // Búsqueda por diagnósticos
    @Query("SELECT DISTINCT e FROM ExpedienteClinico e " +
           "JOIN e.entradasHospital eh " +
           "WHERE LOWER(eh.diagnostico) LIKE LOWER(CONCAT('%', :diagnostico, '%'))")
    List<ExpedienteClinico> buscarExpedientesPorDiagnostico(@Param("diagnostico") String diagnostico);

    // Resumen del expediente
    @Query("SELECT NEW map(" +
           "e.id as expedienteId, " +
           "e.fechaCreacion as fechaCreacion, " +
           "COUNT(eh) as totalEntradas, " +
           "MAX(eh.fecha) as ultimaEntrada) " +
           "FROM ExpedienteClinico e " +
           "LEFT JOIN e.entradasHospital eh " +
           "WHERE e.paciente.id = :pacienteId " +
           "GROUP BY e.id, e.fechaCreacion")
    Map<String, Object> obtenerResumenExpediente(@Param("pacienteId") Long pacienteId);

    // Búsqueda de últimas entradas
    @Query("SELECT eh FROM ExpedienteClinico e " +
           "JOIN e.entradasHospital eh " +
           "WHERE e.paciente.id = :pacienteId " +
           "ORDER BY eh.fecha DESC")
    List<ExpedienteClinico> buscarUltimasEntradas(@Param("pacienteId") Long pacienteId, org.springframework.data.domain.Pageable pageable);

    // Estadísticas por tipo de entrada
    @Query("SELECT eh.tipoEntrada, COUNT(eh) " +
           "FROM ExpedienteClinico e " +
           "JOIN e.entradasHospital eh " +
           "WHERE e.id = :expedienteId " +
           "GROUP BY eh.tipoEntrada")
    List<Object[]> obtenerEstadisticasPorTipoEntrada(@Param("expedienteId") Long expedienteId);
}