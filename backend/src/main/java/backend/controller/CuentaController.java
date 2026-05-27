package backend.controller;

import backend.dto.CuentaDTO;
import backend.dto.MovimientoRequestDTO;
import backend.dto.TransaccionDTO;
import backend.model.Cuenta;
import backend.model.Transaccion;
import backend.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    private final BancoService bancoService;

    @Autowired
    public CuentaController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarTodasCuentas() {
        List<CuentaDTO> dtos = bancoService.listarCuentas().stream()
                .map(this::convertCuentaToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerCuenta(@PathVariable Long id) {
        Cuenta cuenta = bancoService.obtenerCuentaPorId(id);
        return ResponseEntity.ok(convertCuentaToDTO(cuenta));
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO dto) {
        if (dto.getClienteId() == null) {
            throw new IllegalArgumentException("Debe proporcionar un clienteId válido.");
        }
        Cuenta cuenta = bancoService.crearCuenta(dto.getClienteId(), "AHORRO");
        return ResponseEntity.status(HttpStatus.CREATED).body(convertCuentaToDTO(cuenta));
    }

    @PostMapping("/{id}/transacciones")
    public ResponseEntity<TransaccionDTO> registrarTransaccion(@PathVariable Long id, @RequestBody MovimientoRequestDTO request) {
        Transaccion transaccion = bancoService.registrarMovimiento(
                id,
                request.getTipo(),
                request.getMonto(),
                request.getConcepto(),
                request.getCategoria() != null ? request.getCategoria() : "Otros"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(convertTransaccionToDTO(transaccion));
    }

    @GetMapping("/{id}/transacciones")
    public ResponseEntity<List<TransaccionDTO>> listarMovimientos(@PathVariable Long id) {
        List<TransaccionDTO> dtos = bancoService.obtenerMovimientosCuenta(id).stream()
                .map(this::convertTransaccionToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // --- MÉTODOS AUXILIARES DE CONVERSIÓN ---

    private CuentaDTO convertCuentaToDTO(Cuenta cuenta) {
        return CuentaDTO.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .clienteId(cuenta.getCliente().getId())
                .clienteNombre(cuenta.getCliente().getNombre() + " " + cuenta.getCliente().getApellidos())
                .saldo(cuenta.getSaldo())
                .build();
    }

    private TransaccionDTO convertTransaccionToDTO(Transaccion transaccion) {
        return TransaccionDTO.builder()
                .id(transaccion.getId())
                .tipo(transaccion.getTipo())
                .concepto(transaccion.getConcepto())
                .cantidad(transaccion.getCantidad())
                .fecha(transaccion.getFecha())
                .categoria(transaccion.getCategoria())
                .cuentaId(transaccion.getCuenta().getId())
                .build();
    }
}
