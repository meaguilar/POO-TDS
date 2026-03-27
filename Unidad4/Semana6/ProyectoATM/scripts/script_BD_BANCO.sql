-- Crear base de datos
CREATE DATABASE BD_BANCO;
GO

USE BD_BANCO;
GO

-- Tabla Empleado: almacena empleados que registran clientes
CREATE TABLE Empleado (
                          id_empleado INT PRIMARY KEY IDENTITY(1,1),
                          nombre NVARCHAR(100) NOT NULL,
                          correo NVARCHAR(100) UNIQUE NOT NULL,
                          codigo NVARCHAR(20) UNIQUE NOT NULL,
                          cargo NVARCHAR(50),
                          activo BIT DEFAULT 1,
                          usuario NVARCHAR(50) UNIQUE,
                          contrasena NVARCHAR(255)
);
GO

-- Tabla UsuarioATM: clientes registrados por empleados
CREATE TABLE UsuarioATM (
                            id_usuario INT PRIMARY KEY IDENTITY(1,1),
                            nombre NVARCHAR(100) NOT NULL,
                            correo NVARCHAR(100) UNIQUE NOT NULL,
                            usuario_login NVARCHAR(50) UNIQUE NOT NULL,
                            contrasena NVARCHAR(255) NOT NULL,
                            pin NVARCHAR(10),
                            numero_tarjeta NVARCHAR(20) UNIQUE,
                            saldo DECIMAL(18,2) DEFAULT 0.0,
                            codigo_empleado NVARCHAR(20), -- usar codigo en vez de id
                            CONSTRAINT FK_UsuarioATM_Empleado FOREIGN KEY (codigo_empleado)
                                REFERENCES Empleado(codigo) ON DELETE SET NULL
);
