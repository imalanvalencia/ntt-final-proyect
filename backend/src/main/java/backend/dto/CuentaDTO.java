package backend.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaDTO {
    private Long id;
    private String numeroCuenta;
    private Long clienteId;
    private String clienteNombre; // Para mostrar información resumida en el listado
    private BigDecimal saldo;
}
