package backend.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionDTO {
    private Long id;
    private String tipo;
    private String concepto;
    private BigDecimal cantidad;
    private LocalDateTime fecha;
    private String categoria;
    private Long cuentaId;
}
