-- Seed Clientes (Usuarios)
INSERT INTO Usuario (id_usuario, username, password, dni, email, nombre, apellidos, telefono, fecha_registro) VALUES
(1, 'alan.smith', 'password123', '12345678A', 'alan.smith@example.com', 'Alan', 'Smith', '600112233', CURRENT_TIMESTAMP),
(2, 'adrian.garcia', 'adrian2026', '87654321B', 'adrian.garcia@example.com', 'Adrián', 'García', '611223344', CURRENT_TIMESTAMP),
(3, 'maria.lopez', 'maria99', '11223344C', 'maria.lopez@example.com', 'María', 'López', '622334455', CURRENT_TIMESTAMP);

-- Seed Cuentas
INSERT INTO Cuenta (id_cuenta, numero_cuenta, usuario_id, saldo) VALUES
(1, 'ES123456789012345678', 1, 1500.50),
(2, 'ES987654321098765432', 1, 50.00),
(3, 'ES112233445566778899', 2, 3400.00),
(4, 'ES998877665544332211', 3, 0.00);

-- Seed Transacciones
-- Note: for RETIRO, cantidad must be negative. For INGRESO, cantidad must be positive.
INSERT INTO Transaccion (id_transaccion, tipo, concepto, cantidad, fecha, categoria, cuenta_id, cuenta2_id) VALUES
(1, 'INGRESO', 'Nómina mensual', 2000.00, TIMESTAMPADD('DAY', -5, CURRENT_TIMESTAMP), 'Nómina', 1, NULL),
(2, 'RETIRO', 'Compra supermercado', -50.00, TIMESTAMPADD('DAY', -3, CURRENT_TIMESTAMP), 'Alimentación', 1, NULL),
(3, 'RETIRO', 'Pago internet', -49.50, TIMESTAMPADD('DAY', -1, CURRENT_TIMESTAMP), 'Servicios', 1, NULL),
(4, 'INGRESO', 'Transferencia recibida', 50.00, CURRENT_TIMESTAMP, 'Transferencias', 2, NULL),
(5, 'INGRESO', 'Depósito inicial', 3400.00, TIMESTAMPADD('DAY', -10, CURRENT_TIMESTAMP), 'Otros', 3, NULL);
