## El concepto de "Titular" ya cubre la identidad

cliente el diagrama de clases que creamos, la entidad Cuenta ya tiene el campo titular: String (por ejemplo, "Alan Smith").

Para una aplicación de cajero automático (ATM), el usuario no hace un "registro con email y contraseña" tradicional. El usuario introduce una tarjeta que va ligada a un número de cuenta.

Por lo tanto, en este dominio bancario, la Cuenta es el usuario. Identificamos al cliente directamente a través de su número de cuenta.

## Evitamos el "Scope Creep" (Aumento descontrolado del alcance)

Como gestor del proyecto, tu enemigo número uno es el tiempo. Si añadimos una entidad Usuario independiente, la IA se verá obligada a ramificar el código:

Habría que crear UsuarioRepository, UsuarioService, UsuarioController, UsuarioRequestDTO...

Tendrías que gestionar una relación 1:M o 1:1 adicional (Un Usuario tiene Muchas Cuentas).

Tendrías que arrastrar esa complejidad a los formularios reactivos de Angular, obligando al evaluador a loguearse antes de usar el cajero.
