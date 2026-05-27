package backend.service;

import backend.model.Cliente;
import backend.model.Cuenta;
import backend.model.Transaccion;
import backend.repository.ClienteRepository;
import backend.repository.CuentaRepository;
import backend.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class BancoService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;

    @Autowired
    public BancoService(ClienteRepository clienteRepository,
                        CuentaRepository cuentaRepository,
                        TransaccionRepository transaccionRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    // --- CLIENTES ---

    public Cliente registrarCliente(Cliente cliente) {
        if (clienteRepository.findByDni(cliente.getDni()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con el DNI: " + cliente.getDni());
        }
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con el Email: " + cliente.getEmail());
        }
        if (clienteRepository.findByUsername(cliente.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con el Username: " + cliente.getUsername());
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + id));
    }

    // --- CUENTAS ---

    public Cuenta crearCuenta(Long clienteId, String tipo) {
        Cliente cliente = obtenerClientePorId(clienteId);

        String numeroCuenta = generarNumeroCuentaUnico();

        Cuenta cuenta = Cuenta.builder()
                .numeroCuenta(numeroCuenta)
                .cliente(cliente)
                .saldo(BigDecimal.ZERO)
                .build();

        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con id: " + id));
    }

    public List<Cuenta> listarCuentasPorCliente(Long clienteId) {
        // Verificar que el cliente existe
        obtenerClientePorId(clienteId);
        return cuentaRepository.findByClienteId(clienteId);
    }

    // --- OPERACIONES BÁSICAS (TRANSACCIONES) ---

    public Transaccion registrarMovimiento(Long cuentaId, String tipo, BigDecimal monto, String concepto, String categoria) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser superior a cero.");
        }

        Cuenta cuenta = obtenerCuentaPorId(cuentaId);
        BigDecimal cantidadDb;

        if ("RETIRO".equalsIgnoreCase(tipo)) {
            if (cuenta.getSaldo().compareTo(monto) < 0) {
                throw new IllegalStateException("Saldo insuficiente en la cuenta. Saldo actual: " + cuenta.getSaldo());
            }
            // Restar saldo
            cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
            // Cantidad en BD debe ser negativa para retiros
            cantidadDb = monto.negate();
        } else if ("INGRESO".equalsIgnoreCase(tipo)) {
            // Sumar saldo
            cuenta.setSaldo(cuenta.getSaldo().add(monto));
            // Cantidad en BD debe ser positiva para ingresos
            cantidadDb = monto;
        } else {
            throw new IllegalArgumentException("Tipo de movimiento inválido. Debe ser 'INGRESO' o 'RETIRO'.");
        }

        // Guardar cuenta actualizada con el nuevo saldo
        cuentaRepository.save(cuenta);

        // Crear y guardar la transacción
        Transaccion transaccion = Transaccion.builder()
                .tipo(tipo.toUpperCase())
                .concepto(concepto)
                .cantidad(cantidadDb)
                .fecha(LocalDateTime.now())
                .categoria(categoria)
                .cuenta(cuenta)
                .build();

        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> obtenerMovimientosCuenta(Long cuentaId) {
        // Verificar que la cuenta existe
        obtenerCuentaPorId(cuentaId);
        return transaccionRepository.findByCuentaIdOrderByFechaDesc(cuentaId);
    }

    // --- MÉTODOS DE SOPORTE ---

    private String generarNumeroCuentaUnico() {
        Random random = new Random();
        String numeroCuenta;
        do {
            StringBuilder sb = new StringBuilder("ES");
            for (int i = 0; i < 18; i++) {
                sb.append(random.nextInt(10));
            }
            numeroCuenta = sb.toString();
        } while (cuentaRepository.findByNumeroCuenta(numeroCuenta).isPresent());

        return numeroCuenta;
    }
}
