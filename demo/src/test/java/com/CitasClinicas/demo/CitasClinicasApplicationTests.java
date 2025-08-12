package com.CitasClinicas.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.CitasClinicas.demo.Repositorios.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CitasClinicasApplicationTests {

    @Autowired
    private IMedicoRepositorio medicoRepositorio;

    @Autowired
    private IPacienteRepositorio pacienteRepositorio;

    @Autowired
    private ICitaRepositorio citaRepositorio;

    @Autowired
    private IExpedienteClinicoRepositorio expedienteRepositorio;

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring se carga correctamente
        assertNotNull(medicoRepositorio, "El repositorio de médicos no debería ser null");
        assertNotNull(pacienteRepositorio, "El repositorio de pacientes no debería ser null");
        assertNotNull(citaRepositorio, "El repositorio de citas no debería ser null");
        assertNotNull(expedienteRepositorio, "El repositorio de expedientes no debería ser null");
    }

    @Test
    void verificarConexionBaseDatos() {
        // Verifica que podemos realizar operaciones básicas en la base de datos
        assertTrue(medicoRepositorio.count() >= 0, "Debería poder contar médicos");
        assertTrue(pacienteRepositorio.count() >= 0, "Debería poder contar pacientes");
        assertTrue(citaRepositorio.count() >= 0, "Debería poder contar citas");
        assertTrue(expedienteRepositorio.count() >= 0, "Debería poder contar expedientes");
    }

    @Test
    void verificarConsultasPersonalizadas() {
        // Verifica que las consultas personalizadas estén funcionando
        assertDoesNotThrow(() -> {
            citaRepositorio.findCitasByEstado("PENDIENTE");
            medicoRepositorio.findByEspecialidad("GENERAL");
            pacienteRepositorio.findByNombreContaining("Test");
        }, "Las consultas personalizadas deberían ejecutarse sin errores");
    }
}
