package com.CitasClinicas.demo.Repositorios;

import com.CitasClinicas.demo.Modelos.EntradaHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;

public interface IEntradaHistorialRepositorio extends JpaRepository<EntradaHistorial, Long> {
    // Búsquedas básicas por expediente
    @Query("SELECT e FROM EntradaHistorial e WHERE e.expedienteClinico.id = :expedienteId ORDER BY e.fecha DESC")
    List<EntradaHistorial> buscarEntradasPorExpediente(@Param("expedienteId") Long expedienteId);
    
    // Búsquedas por fecha y tipo
    @Query("SELECT e FROM EntradaHistorial e WHERE e.fecha BETWEEN :inicio AND :fin AND e.tipoEntrada = :tipo")
    List<EntradaHistorial> buscarEntradasPorRangoYTipo(
        @Param("inicio") Date inicio,
        @Param("fin") Date fin,
        @Param("tipo") String tipo
    );
    
    // Búsquedas por diagnóstico
    @Query("SELECT e FROM EntradaHistorial e WHERE LOWER(e.diagnostico) LIKE LOWER(CONCAT('%', :diagnostico, '%'))")
    List<EntradaHistorial> buscarEntradasPorDiagnostico(@Param("diagnostico") String diagnostico);
    
    // Estadísticas
    @Query("SELECT e.tipoEntrada, COUNT(e) FROM EntradaHistorial e WHERE e.expedienteClinico.id = :expedienteId GROUP BY e.tipoEntrada")
    List<Object[]> contarEntradasPorTipo(@Param("expedienteId") Long expedienteId);
    
    // Búsquedas avanzadas
    @Query("SELECT e FROM EntradaHistorial e WHERE e.expedienteClinico.id = :expedienteId " +
           "AND e.fecha >= :fecha AND e.tipoEntrada = :tipo ORDER BY e.fecha DESC")
    List<EntradaHistorial> buscarEntradasRecientesPorTipo(
        @Param("expedienteId") Long expedienteId,
        @Param("fecha") Date fecha,
        @Param("tipo") String tipo
    );
    
    @Query("SELECT e FROM EntradaHistorial e " +
           "WHERE e.expedienteClinico.paciente.id = :pacienteId " +
           "AND e.fecha >= :fecha " +
           "ORDER BY e.fecha DESC")
    List<EntradaHistorial> buscarHistorialRecientePaciente(
        @Param("pacienteId") Long pacienteId,
        @Param("fecha") Date fecha
    );

    // Búsqueda con resumen
    @Query("SELECT NEW map(e.fecha as fecha, e.tipoEntrada as tipo, e.diagnostico as diagnostico, " +
           "e.tratamiento as tratamiento) FROM EntradaHistorial e " +
           "WHERE e.expedienteClinico.id = :expedienteId " +
           "ORDER BY e.fecha DESC")
    List<java.util.Map<String, Object>> obtenerResumenHistorial(@Param("expedienteId") Long expedienteId);
}