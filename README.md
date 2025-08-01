# ClinicaPoryectoFinal
trabajen

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS CitasClinicas;
USE CitasClinicas;

-- Tabla Usuario
CREATE TABLE Usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(150) UNIQUE,
    telefono VARCHAR(20),
    passwordHash VARCHAR(255),
    rol VARCHAR(50),
    fechaRegistro DATE,
    activo BOOLEAN
);

-- Tabla EntradaHistorial
CREATE TABLE EntradaHistorial (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    tipoEntrada VARCHAR(100),
    descripcion TEXT,
    detallesAdicionales TEXT,
    idProfesionalResponsable BIGINT,
    FOREIGN KEY (idProfesionalResponsable) REFERENCES Usuario(id)
);

-- Tabla ExpedienteClinico
CREATE TABLE ExpedienteClinico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fechaCreacion DATE
);

-- Tabla Paciente
CREATE TABLE Paciente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fechaNacimiento DATE,
    direccion TEXT,
    idUsuario BIGINT UNIQUE,
    idExpedienteClinico BIGINT UNIQUE,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id),
    FOREIGN KEY (idExpedienteClinico) REFERENCES ExpedienteClinico(id)
);

-- Tabla Medico
CREATE TABLE Medico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    especialidad VARCHAR(100),
    licenciaMedica VARCHAR(100),
    experiencia TEXT,
    idUsuario BIGINT UNIQUE,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id)
);

-- Tabla Clinica
CREATE TABLE Clinica (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150),
    direccion TEXT,
    telefono VARCHAR(20),
    email VARCHAR(150)
);

-- Relación de empleo entre Médico y Clínica (muchos a muchos)
CREATE TABLE Clinica_Medico (
    idClinica BIGINT,
    idMedico BIGINT,
    PRIMARY KEY (idClinica, idMedico),
    FOREIGN KEY (idClinica) REFERENCES Clinica(id),
    FOREIGN KEY (idMedico) REFERENCES Medico(id)
);

-- Tabla CupoDeAtencion
CREATE TABLE CupoDeAtencion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    horaInicio TIME,
    horaFin TIME,
    costo DECIMAL(10,2),
    tipoServicio VARCHAR(100),
    estado VARCHAR(50),
    idMedico BIGINT,
    FOREIGN KEY (idMedico) REFERENCES Medico(id)
);

-- Tabla Cita
CREATE TABLE Cita (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fechaHora DATETIME,
    estado VARCHAR(50),
    motivo TEXT,
    notaAtencion TEXT,
    costo DECIMAL(10,2),
    idPaciente BIGINT,
    idCupoDeAtencion BIGINT,
    FOREIGN KEY (idPaciente) REFERENCES Paciente(id),
    FOREIGN KEY (idCupoDeAtencion) REFERENCES CupoDeAtencion(id)
);

-- Relación entre Expediente y EntradaHistorial (muchas entradas por expediente)
CREATE TABLE Expediente_Entrada (
    idExpediente BIGINT,
    idEntrada BIGINT,
    PRIMARY KEY (idExpediente, idEntrada),
    FOREIGN KEY (idExpediente) REFERENCES ExpedienteClinico(id),
    FOREIGN KEY (idEntrada) REFERENCES EntradaHistorial(id)
);
