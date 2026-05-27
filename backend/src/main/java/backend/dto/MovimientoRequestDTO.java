package backend.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRequestDTO {
    private String tipo;      // "INGRESO" o "RETIRO"
    private BigDecimal monto;  // Importe en valor positivo
    private String concepto;
    private String categoria;
}
