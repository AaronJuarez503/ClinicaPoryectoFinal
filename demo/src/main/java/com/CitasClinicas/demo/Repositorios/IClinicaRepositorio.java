package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClinicaRepositorio extends JpaRepository<Clinica, Long> {
    // Búsquedas básicas
    List<Clinica> findByNombre(String nombre);
    List<Clinica> findByNombreContaining(String nombre);
    List<Clinica> findByDireccionContaining(String direccion);
    
    // Búsquedas por correo y teléfono
    Clinica findByEmail(String email);
    Clinica findByTelefono(String telefono);
    
    // Búsquedas por médicos asociados
    @Query("SELECT c FROM Clinica c JOIN c.medicos m WHERE m.id = :medicoId")
    List<Clinica> findByMedicoId(@Param("medicoId") Long medicoId);
    
    // Búsquedas combinadas
    @Query("SELECT c FROM Clinica c WHERE c.direccion LIKE %:zona% AND SIZE(c.medicos) >= :minMedicos")
    List<Clinica> buscarClinicasPorZonaYCantidadMedicos(
        @Param("zona") String zona,
        @Param("minMedicos") int minMedicos
    );
    
    // Estadísticas
    @Query("SELECT c.nombre, COUNT(ct) FROM Clinica c LEFT JOIN c.citas ct GROUP BY c.nombre")
    List<Object[]> contarCitasPorClinica();
}