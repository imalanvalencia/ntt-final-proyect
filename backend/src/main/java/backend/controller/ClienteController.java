package backend.controller;

import backend.dto.ClienteDTO;
import backend.dto.CuentaDTO;
import backend.model.Cliente;
import backend.model.Cuenta;
import backend.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final BancoService bancoService;

    @Autowired
    public ClienteController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> dtos = bancoService.listarClientes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = bancoService.obtenerClientePorId(id);
        return ResponseEntity.ok(convertToDTO(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody ClienteDTO dto) {
        Cliente cliente = Cliente.builder()
                .username(dto.getUsername())
                .password(dto.getPassword() != null ? dto.getPassword() : "default_pass")
                .dni(dto.getDni())
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .telefono(dto.getTelefono())
                .build();

        Cliente registrado = bancoService.registrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(registrado));
    }

    @GetMapping("/{id}/cuentas")
    public ResponseEntity<List<CuentaDTO>> listarCuentasDeCliente(@PathVariable Long id) {
        List<CuentaDTO> dtos = bancoService.listarCuentasPorCliente(id).stream()
                .map(this::convertCuentaToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // --- MÉTODOS AUXILIARES DE CONVERSIÓN ---

    private ClienteDTO convertToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .username(cliente.getUsername())
                .dni(cliente.getDni())
                .email(cliente.getEmail())
                .nombre(cliente.getNombre())
                .apellidos(cliente.getApellidos())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

    private CuentaDTO convertCuentaToDTO(Cuenta cuenta) {
        return CuentaDTO.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .clienteId(cuenta.getCliente().getId())
                .clienteNombre(cuenta.getCliente().getNombre() + " " + cuenta.getCliente().getApellidos())
                .saldo(cuenta.getSaldo())
                .build();
    }
}
