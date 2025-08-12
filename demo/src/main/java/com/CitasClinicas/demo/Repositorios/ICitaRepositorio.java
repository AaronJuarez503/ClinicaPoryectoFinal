package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface ICitaRepositorio extends JpaRepository<Cita, Long> {
    // Búsquedas por paciente
    @Query("SELECT c FROM Cita c WHERE c.paciente.id = :pacienteId AND c.fechaHora BETWEEN :fechaInicio AND :fechaFin")
    List<Cita> buscarCitasPorPacienteYRangoFecha(
        @Param("pacienteId") Long pacienteId,
        @Param("fechaInicio") Date fechaInicio,
        @Param("fechaFin") Date fechaFin
    );

    // Búsquedas por clínica
    @Query("SELECT c FROM Cita c WHERE c.clinica.id = :clinicaId AND c.estado = :estado AND FUNCTION('DATE', c.fechaHora) = FUNCTION('DATE', :fecha)")
    List<Cita> buscarCitasPorClinicaEstadoYFecha(
        @Param("clinicaId") Long clinicaId,
        @Param("estado") String estado,
        @Param("fecha") Date fecha
    );

    // Búsqueda de próximas citas
    @Query("SELECT c FROM Cita c WHERE c.clinica.id = :clinicaId AND c.fechaHora >= CURRENT_DATE ORDER BY c.fechaHora ASC")
    List<Cita> buscarProximasCitasClinica(@Param("clinicaId") Long clinicaId);

    // Búsquedas por email del paciente
    @Query("SELECT c FROM Cita c JOIN c.paciente p WHERE p.email = :email AND c.estado = 'Pendiente'")
    List<Cita> buscarCitasPendientesPorEmailPaciente(@Param("email") String email);

    // Estadísticas
    @Query("SELECT COUNT(c), c.estado FROM Cita c WHERE c.clinica.id = :clinicaId GROUP BY c.estado")
    List<Object[]> contarCitasPorEstadoYClinica(@Param("clinicaId") Long clinicaId);

    // Búsquedas avanzadas
    @Query("SELECT c FROM Cita c WHERE c.estado = :estado AND c.fechaHora >= CURRENT_DATE ORDER BY c.fechaHora ASC")
    List<Cita> buscarCitasFuturasPorEstado(@Param("estado") String estado);

    @Query("SELECT COUNT(c) FROM Cita c WHERE c.paciente.id = :pacienteId AND c.estado = 'Pendiente'")
    Long contarCitasPendientesPorPaciente(@Param("pacienteId") Long pacienteId);

    @Query("SELECT c FROM Cita c WHERE c.clinica.id = :clinicaId AND MONTH(c.fechaHora) = :mes AND YEAR(c.fechaHora) = :anio")
    List<Cita> buscarCitasPorClinicaYMes(
        @Param("clinicaId") Long clinicaId,
        @Param("mes") int mes,
        @Param("anio") int anio
    );

    // Método derivado de Spring Data JPA
    List<Cita> findByPacienteIdAndEstado(Long pacienteId, String estado);
}
