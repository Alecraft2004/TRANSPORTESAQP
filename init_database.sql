-- ============================================================================
-- SCRIPT DE INICIALIZACIÓN DE DATABASE PARA TRANSPORTESAQP
-- ============================================================================
-- Base de datos: transportesaqp
-- Motor: PostgreSQL
-- Version: 2.0
-- ============================================================================

-- Crear base de datos (ejecutar como superusuario)
-- CREATE DATABASE transportesaqp;

-- Ejecutar los siguientes comandos EN transportesaqp

-- ============================================================================
-- TABLA: usuarios
-- ============================================================================
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLA: cliente
-- ============================================================================
CREATE TABLE IF NOT EXISTS cliente (
    codigo SERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    sexo CHAR(1),
    correo VARCHAR(100),
    celular VARCHAR(20),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLA: tecnico
-- ============================================================================
CREATE TABLE IF NOT EXISTS tecnico (
    codigo SERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    sexo CHAR(1),
    correo VARCHAR(100),
    celular VARCHAR(20),
    especialidad VARCHAR(100),
    tiempo_servicio INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLA: vehiculo
-- ============================================================================
CREATE TABLE IF NOT EXISTS vehiculo (
    codigo SERIAL PRIMARY KEY,
    placa VARCHAR(20) UNIQUE NOT NULL,
    numero_serie VARCHAR(50),
    anho_fabricacion INT,
    color VARCHAR(50),
    cantidad_puertas INT,
    cilindrada DOUBLE PRECISION,
    duenho VARCHAR(100),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLA: plan_mantenimiento
-- ============================================================================
CREATE TABLE IF NOT EXISTS plan_mantenimiento (
    id SERIAL PRIMARY KEY,
    id_tecnico INT NOT NULL REFERENCES tecnico(codigo) ON DELETE CASCADE,
    id_vehiculo INT NOT NULL REFERENCES vehiculo(codigo) ON DELETE CASCADE,
    tipo_mantenimiento VARCHAR(100),
    kilometraje INT,
    falla TEXT,
    fecha DATE,
    servicio_pago NUMERIC(10, 2),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- ÍNDICES PARA OPTIMIZACIÓN
-- ============================================================================
CREATE INDEX IF NOT EXISTS idx_usuario ON usuarios(usuario);
CREATE INDEX IF NOT EXISTS idx_cliente_nombres ON cliente(nombres);
CREATE INDEX IF NOT EXISTS idx_tecnico_nombres ON tecnico(nombres);
CREATE INDEX IF NOT EXISTS idx_vehiculo_placa ON vehiculo(placa);
CREATE INDEX IF NOT EXISTS idx_plan_tecnico ON plan_mantenimiento(id_tecnico);
CREATE INDEX IF NOT EXISTS idx_plan_vehiculo ON plan_mantenimiento(id_vehiculo);

-- ============================================================================
-- DATOS DE PRUEBA
-- ============================================================================

-- Usuarios
INSERT INTO usuarios (usuario, contrasena) VALUES 
('admin', 'admin123'),
('usuario1', 'pass123'),
('usuario2', 'pass456')
ON CONFLICT (usuario) DO NOTHING;

-- Clientes
INSERT INTO cliente (nombres, apellidos, direccion, sexo, correo, celular) VALUES 
('Juan', 'Pérez', 'Calle 1 #123', 'M', 'juan@email.com', '3101234567'),
('María', 'García', 'Avenida 2 #456', 'F', 'maria@email.com', '3109876543'),
('Carlos', 'López', 'Calle 3 #789', 'M', 'carlos@email.com', '3105555555')
ON CONFLICT DO NOTHING;

-- Técnicos
INSERT INTO tecnico (nombres, apellidos, direccion, sexo, correo, celular, especialidad, tiempo_servicio) VALUES 
('Andrés', 'Martínez', 'Calle 10', 'M', 'andres@email.com', '3201111111', 'Motores', 5),
('Diego', 'Rodríguez', 'Calle 11', 'M', 'diego@email.com', '3202222222', 'Eléctrica', 3),
('Roberto', 'Sánchez', 'Calle 12', 'M', 'roberto@email.com', '3203333333', 'Transmisión', 7)
ON CONFLICT DO NOTHING;

-- Vehículos
INSERT INTO vehiculo (placa, numero_serie, anho_fabricacion, color, cantidad_puertas, cilindrada, duenho) VALUES 
('ABC123', 'VIN123456789', 2020, 'Blanco', 4, 2000.0, 'Juan Pérez'),
('DEF456', 'VIN987654321', 2021, 'Negro', 4, 2200.0, 'María García'),
('GHI789', 'VIN111111111', 2019, 'Rojo', 2, 1600.0, 'Carlos López')
ON CONFLICT (placa) DO NOTHING;

-- ============================================================================
-- CONSULTAS PARA VERIFICACIÓN
-- ============================================================================

-- Verificar que las tablas se crearon correctamente
SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'public' 
AND table_type = 'BASE TABLE';

-- Ver registros creados
SELECT COUNT(*) as total_usuarios FROM usuarios;
SELECT COUNT(*) as total_clientes FROM cliente;
SELECT COUNT(*) as total_tecnicos FROM tecnico;
SELECT COUNT(*) as total_vehiculos FROM vehiculo;

-- ============================================================================
-- FIN DEL SCRIPT
-- ============================================================================
