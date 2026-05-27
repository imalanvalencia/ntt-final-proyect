-- Seed Clientes (Usuarios)
INSERT INTO Usuario (username, password, dni, email, nombre, apellidos, telefono, fecha_registro) VALUES
('alan.smith', 'password123', '12345678A', 'alan.smith@example.com', 'Alan', 'Smith', '600112233', CURRENT_TIMESTAMP),
('adrian.garcia', 'adrian2026', '87654321B', 'adrian.garcia@example.com', 'Adrián', 'García', '611223344', CURRENT_TIMESTAMP),
('maria.lopez', 'maria99', '11223344C', 'maria.lopez@example.com', 'María', 'López', '622334455', CURRENT_TIMESTAMP);

-- Seed Cuentas
INSERT INTO Cuenta (numero_cuenta, usuario_id, saldo) VALUES
('ES123456789012345678', 1, 1500.50),
('ES987654321098765432', 1, 50.00),
('ES112233445566778899', 2, 3400.00),
('ES998877665544332211', 3, 0.00);

-- Seed Transacciones
-- Note: for RETIRO, cantidad must be negative. For INGRESO, cantidad must be positive.
INSERT INTO Transaccion (tipo, concepto, cantidad, fecha, categoria, cuenta_id, cuenta2_id) VALUES
('INGRESO', 'Nómina mensual', 2000.00, TIMESTAMPADD('DAY', -5, CURRENT_TIMESTAMP), 'Nómina', 1, NULL),
('RETIRO', 'Compra supermercado', -50.00, TIMESTAMPADD('DAY', -3, CURRENT_TIMESTAMP), 'Alimentación', 1, NULL),
('RETIRO', 'Pago internet', -49.50, TIMESTAMPADD('DAY', -1, CURRENT_TIMESTAMP), 'Servicios', 1, NULL),
('INGRESO', 'Transferencia recibida', 50.00, CURRENT_TIMESTAMP, 'Transferencias', 2, NULL),
('INGRESO', 'Depósito inicial', 3400.00, TIMESTAMPADD('DAY', -10, CURRENT_TIMESTAMP), 'Otros', 3, NULL);
