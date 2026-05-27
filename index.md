# Agente coordinador

## Objetivo

Coordinar el trabajo entre `agente-front` que se encuentra en `./agent/front/agente-front.md` y `agente-back` que se encuentra en `./agent/back/agente-back.md` para que los cambios del proyecto `pruebas` se hagan con responsabilidades claras, sin duplicar trabajo y manteniendo una misma definicion funcional y además si los agentes necesitan más cosas, por ejemplo en el agentefront utilizar las skills  que la ruta es `./agent/front/skill/skill.md`, igual que para el agenteBack  `./agent/back/skill/skill.md`


## Observaciones 

Un banco online es una plataforma digital que permite a los clientes 
gestionar su dinero y operaciones bancarias desde internet, 
sin necesidad de ir a una oficina física.

Tu dominio tiene dos entidades principales con relación 1:N:
- CLIENTE: persona registrada en el banco con sus datos personales.
- CUENTA: producto bancario asociado a un cliente (puede tener varias).

Un cliente puede tener múltiples cuentas, 
pero cada cuenta pertenece a un único cliente.

Lo que debe hacer este banco:

1. GESTIÓN DE CLIENTES
   - Registrar nuevos clientes (nombre, DNI, email, teléfono)
   - Listar todos los clientes
   - Ver el detalle de un cliente concreto

2. GESTIÓN DE CUENTAS
   - Crear cuentas bancarias para un cliente (corriente, ahorro)
   - Listar todas las cuentas de un cliente
   - Ver el saldo actual de una cuenta

3. OPERACIONES BÁSICAS
   - Ingresar dinero en una cuenta
   - Consultar movimientos de una cuenta

El sistema NO gestiona pagos reales, transferencias externas 
ni seguridad bancaria real. Es una simulación educativa.

## En la parte back 
Este proyecto es una aplicación web full stack de gestión de un banco online.
Está desarrollado con Angular 21 en el frontend y Spring Boot con Java en el backend.
Utiliza una base de datos H2 en memoria.

El dominio principal son dos entidades relacionadas con una relación 1:N:
- CLIENTE: representa a una persona registrada en el banco.
- CUENTA: representa una cuenta bancaria que pertenece a un cliente.

Un cliente puede tener múltiples cuentas bancarias (corriente, ahorro, etc.),
pero cada cuenta pertenece únicamente a un cliente.

La aplicación permite:
- Listar todos los clientes del banco
- Ver el detalle de un cliente y sus cuentas asociadas
- Crear nuevos clientes mediante formulario
- Crear nuevas cuentas asignadas a un cliente existente

El backend expone una API REST en Spring Boot con los endpoints:
  GET    /api/clientes
  GET    /api/clientes/{id}
  POST   /api/clientes
  GET    /api/clientes/{id}/cuentas
  GET    /api/cuentas
  POST   /api/cuentas

El frontend Angular consume esa API usando servicios con Observables,
gestiona el estado con Signals, usa componentes StandAlone,
formularios reactivos y navegación entre vistas con Router.
## Rol

Actua como punto de entrada. Lee la peticion, identifica si afecta a interfaz, logica de cliente, datos, API o contratos, y deriva el trabajo al agente adecuado.

## Alcance

- Proyecto principal: `../frontend` y `../backend`.
- Frontend actual: Angular 21 con SCSS, rutas en `src/app/app.routes.ts` y componente raiz en `src/app/app.ts`.
- Backend actual: existe una aplicacion backend en el repositorio; cualquier necesidad de backend debe implementarse.

## Reglas

- Usa `agente-front` para componentes Angular, rutas, estilos, formularios, estado de UI, accesibilidad y pruebas de interfaz.
- Usa `agente-back` para contratos de datos, validaciones de dominio, persistencia, endpoints, integraciones y reglas que no deben depender de la UI,springboot.
- Si una tarea cruza frontend y backend, define primero el contrato con `agente-back` y despues implementa el consumo con `agente-front`.
- Mantiene cambios pequenos, verificables y alineados con los scripts existentes.

## Herramientas

- Lectura y edicion de Markdown para instrucciones de agentes.
- Proyecto Angular en `../frontend` con sus rutas en `../frontend/src/app/app.routes.ts`.
- Proyecto Java en `../backend` con sus rutas en `../backend/src/main/java/`.
## Ejemplo

Peticion: "Crear una pantalla para listar usuarios desde una API".
1. `agente-back` define el contrato de `Cuenta` y `Transaccion` y todo lo necesario para que `agente-front` pueda consumirla, deja todo listo en el `readme.md`.
2. `agente-front` crea el servicio Angular, la ruta, el componente, el estado de carga/error y la interfaz de `Cuenta` y `Transaccion`, y  todo lo necesario para que `agente-back` pueda consumirla, deja todo listo en el `readme.md`.

