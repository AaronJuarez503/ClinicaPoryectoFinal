package com.CitasClinicas.demo.Repositorios;
import com.CitasClinicas.demo.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;
import java.util.Map;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Búsquedas por autenticación
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.activo = true")
    Usuario buscarUsuarioPorEmail(@Param("email") String email);
    
    // Búsquedas por rol
    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol AND u.activo = true")
    List<Usuario> buscarUsuariosPorRol(@Param("rol") String rol);
    
    // Búsquedas por estado y fecha
    @Query("SELECT u FROM Usuario u WHERE u.activo = :activo AND u.fechaRegistro >= :fecha")
    List<Usuario> buscarUsuariosPorEstadoYFecha(
        @Param("activo") boolean activo,
        @Param("fecha") Date fecha
    );
    
    // Búsquedas por nombre o apellido
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(u.apellido) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Usuario> buscarUsuariosPorNombreOApellido(@Param("busqueda") String busqueda);
    
    // Estadísticas
    @Query("SELECT u.rol, COUNT(u) FROM Usuario u GROUP BY u.rol")
    List<Object[]> contarUsuariosPorRol();
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.fechaRegistro >= :fecha")
    Long contarNuevosUsuarios(@Param("fecha") Date fecha);
    
    // Búsquedas avanzadas
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro >= :fecha AND u.activo = true")
    List<Usuario> buscarUsuariosActivos(@Param("fecha") Date fecha);
    
    // Búsquedas por estado y rol
    @Query("SELECT u FROM Usuario u WHERE u.activo = :activo AND u.rol = :rol")
    List<Usuario> buscarUsuariosPorEstadoYRol(
        @Param("activo") boolean activo,
        @Param("rol") String rol
    );
    
    // Búsqueda de usuarios recientes
    @Query("SELECT u FROM Usuario u WHERE u.fechaRegistro >= :fecha ORDER BY u.fechaRegistro DESC")
    List<Usuario> buscarUsuariosRecientes(@Param("fecha") Date fecha);
    
    // Resumen de usuario
    @Query("SELECT NEW map(" +
           "u.id as userId, " +
           "u.nombre as nombre, " +
           "u.apellido as apellido, " +
           "u.email as email, " +
           "u.rol as rol, " +
           "u.fechaRegistro as fechaRegistro, " +
           "u.activo as activo) " +
           "FROM Usuario u WHERE u.id = :userId")
    Map<String, Object> obtenerResumenUsuario(@Param("userId") Long userId);
}