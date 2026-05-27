package backend.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    private String username;
    private String password; // Solo usado para la creación, omitido al retornar
    private String dni;
    private String email;
    private String nombre;
    private String apellidos;
    private String telefono;
    private LocalDateTime fechaRegistro;
}
