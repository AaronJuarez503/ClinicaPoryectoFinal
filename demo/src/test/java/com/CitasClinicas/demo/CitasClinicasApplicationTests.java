package com.CitasClinicas.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.CitasClinicas.demo.Repositorios.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CitasClinicasApplicationTests {

    @Autowired
    private IExpedienteClinicoRepositorio expedienteClinicoRepositorio;

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IClinicaRepositorio clinicaRepositorio;

    @Autowired
    private ICupoDeAtencionRepositorio cupoDeAtencionRepositorio;

    @Test
    void contextLoads() {
        assertNotNull(expedienteClinicoRepositorio);
        assertNotNull(usuarioRepositorio);
        assertNotNull(clinicaRepositorio);
        assertNotNull(cupoDeAtencionRepositorio);
    }

}