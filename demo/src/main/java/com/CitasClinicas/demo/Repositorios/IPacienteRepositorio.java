package com.CitasClinicas.demo.Repositorios;

import com.CitasClinicas.demo.Modelos.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;
import java.util.Map;

public interface IPacienteRepositorio extends JpaRepository<Paciente, Long> {

    List<Paciente> findByNombreContaining(String nombre);

    // Búsquedas por datos personales
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(p.apellido) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Paciente> buscarPacientesPorNombreOApellido(@Param("busqueda") String busqueda);

    // CORRECCIÓN: Se cambió 'p.activo' a 'p.active' para que coincida con el campo en Usuario.java
    @Query("SELECT p FROM Paciente p WHERE p.email = :email AND p.active = true")
    Paciente buscarPacientePorEmail(@Param("email") String email);

    // Búsquedas por fecha
    @Query("SELECT p FROM Paciente p WHERE p.fechaNacimiento BETWEEN :inicio AND :fin")
    List<Paciente> buscarPacientesPorRangoEdad(
        @Param("inicio") Date inicio,
        @Param("fin") Date fin
    );

    // Búsquedas por citas
    @Query("SELECT DISTINCT p FROM Paciente p JOIN p.citas c " +
           "WHERE c.estado = :estado AND c.fechaHora >= CURRENT_DATE")
    List<Paciente> buscarPacientesConCitasPendientes(@Param("estado") String estado);

    // Búsquedas por expediente
    @Query("SELECT DISTINCT p FROM Paciente p JOIN p.expedienteClinico ec JOIN ec.entradas eh " +
           "WHERE LOWER(eh.diagnostico) LIKE LOWER(CONCAT('%', :condicion, '%'))")
    List<Paciente> buscarPacientesPorCondicionMedica(@Param("condicion") String condicion);
    
    // Estadísticas
    @Query("SELECT COUNT(p) FROM Paciente p WHERE p.fechaRegistro >= :fecha")
    Long contarNuevosPacientes(@Param("fecha") Date fecha);

    @Query("SELECT p, COUNT(c) as totalCitas FROM Paciente p " +
           "LEFT JOIN p.citas c " +
           "WHERE c.fechaHora BETWEEN :inicio AND :fin " +
           "GROUP BY p ORDER BY COUNT(c) DESC")
    List<Object[]> obtenerPacientesPorFrecuenciaCitas(
        @Param("inicio") Date inicio,
        @Param("fin") Date fin
    );

    // Búsquedas avanzadas
    // CORRECCIÓN: Se cambió 'p.activo' a 'p.active' para que coincida con el campo en Usuario.java
    @Query("SELECT p FROM Paciente p WHERE p.active = true " +
           "AND EXISTS (SELECT c FROM Cita c WHERE c.paciente = p " +
           "AND c.fechaHora >= :fecha)")
    List<Paciente> buscarPacientesActivosConCitasFuturas(@Param("fecha") Date fecha);

    // Resumen del paciente
    @Query("SELECT NEW map(" +
           "p.id as pacienteId, " +
           "p.nombre as nombre, " +
           "p.apellido as apellido, " +
           "p.email as email, " +
           "p.fechaNacimiento as fechaNacimiento, " +
           "COUNT(c) as totalCitas, " +
           "MAX(c.fechaHora) as ultimaCita, " +
           "COUNT(DISTINCT ec) as tieneExpediente) " +
           "FROM Paciente p " +
           "LEFT JOIN p.citas c " +
           "LEFT JOIN p.expedienteClinico ec " +
           "WHERE p.id = :pacienteId " +
           "GROUP BY p.id, p.nombre, p.apellido, p.email, p.fechaNacimiento")
    Map<String, Object> obtenerResumenPaciente(@Param("pacienteId") Long pacienteId);

    // Búsqueda por última visita
    @Query("SELECT p FROM Paciente p LEFT JOIN p.citas c " +
           "GROUP BY p HAVING MAX(c.fechaHora) <= :fecha OR MAX(c.fechaHora) IS NULL")
    List<Paciente> buscarPacientesSinVisitasRecientes(@Param("fecha") Date fecha);
}