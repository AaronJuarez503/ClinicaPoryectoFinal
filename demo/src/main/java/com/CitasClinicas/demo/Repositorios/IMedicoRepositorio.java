package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;
import java.util.Map;

public interface IMedicoRepositorio extends JpaRepository<Medico, Long> {

    List<Medico> findByEspecialidad(String especialidad);
    @Query("SELECT m FROM Medico m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(m.apellido) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Medico> buscarMedicosPorNombreOApellido(@Param("busqueda") String busqueda);
    
    // Búsquedas por especialidad y licencia
    @Query("SELECT m FROM Medico m WHERE m.especialidad = :especialidad AND m.activo = true")
    List<Medico> buscarMedicosPorEspecialidad(@Param("especialidad") String especialidad);
    
    @Query("SELECT m FROM Medico m WHERE m.licenciaMedica = :licencia")
    Medico buscarMedicoPorLicencia(@Param("licencia") String licencia);
    
    // Búsquedas por clínica
    @Query("SELECT m FROM Medico m JOIN m.clinicas c WHERE c.id = :clinicaId")
    List<Medico> buscarMedicosPorClinica(@Param("clinicaId") Long clinicaId);
    
    // Búsquedas por disponibilidad
    @Query("SELECT DISTINCT m FROM Medico m JOIN m.cuposDeAtencion c " +
           "WHERE c.fecha = :fecha AND c.estado = 'Disponible'")
    List<Medico> buscarMedicosDisponiblesEnFecha(@Param("fecha") Date fecha);
    
    // Búsquedas avanzadas
    @Query("SELECT m FROM Medico m JOIN m.clinicas c " +
           "WHERE c.id = :clinicaId AND m.especialidad = :especialidad AND m.activo = true")
    List<Medico> buscarMedicosPorClinicaYEspecialidad(
        @Param("clinicaId") Long clinicaId,
        @Param("especialidad") String especialidad
    );
    
    // Estadísticas
    @Query("SELECT m.especialidad, COUNT(m) FROM Medico m GROUP BY m.especialidad")
    List<Object[]> contarMedicosPorEspecialidad();
    
    @Query("SELECT m, COUNT(c) FROM Medico m LEFT JOIN m.cuposDeAtencion c " +
           "WHERE c.fecha BETWEEN :inicio AND :fin GROUP BY m ORDER BY COUNT(c) DESC")
    List<Object[]> obtenerMedicosPorCargaTrabajo(
        @Param("inicio") Date inicio,
        @Param("fin") Date fin
    );
    
    // Búsqueda de próximos cupos
    @Query("SELECT m FROM Medico m WHERE EXISTS (" +
           "SELECT c FROM CupoDeAtencion c WHERE c.medico = m " +
           "AND c.fecha >= CURRENT_DATE AND c.estado = 'Disponible')")
    List<Medico> buscarMedicosConCuposDisponibles();
    
    // Resumen del médico
    @Query("SELECT NEW map(" +
           "m.id as medicoId, " +
           "m.nombre as nombre, " +
           "m.apellido as apellido, " +
           "m.especialidad as especialidad, " +
           "m.licenciaMedica as licencia, " +
           "COUNT(DISTINCT c) as totalClinicas, " +
           "COUNT(ca) as totalCupos) " +
           "FROM Medico m " +
           "LEFT JOIN m.clinicas c " +
           "LEFT JOIN m.cuposDeAtencion ca " +
           "WHERE m.id = :medicoId " +
           "GROUP BY m.id, m.nombre, m.apellido, m.especialidad, m.licenciaMedica")
    Map<String, Object> obtenerResumenMedico(@Param("medicoId") Long medicoId);
}