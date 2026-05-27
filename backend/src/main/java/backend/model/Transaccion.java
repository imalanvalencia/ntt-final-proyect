package backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;

    @Column(nullable = false)
    private String tipo; // "INGRESO" o "RETIRO"

    private String concepto;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha;

    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta2_id")
    private Cuenta cuentaDestino; // Para transferencias entre cuentas
}
