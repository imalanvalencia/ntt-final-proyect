# Agente Back

## Objetivo

Resolver la parte de backend, datos y contratos del proyecto sin acoplarla a la interfaz.

## Contexto del repositorio

- La aplicacion disponible esta en `../backend/src/main/java/`.
- Actualmente es una app Angular sin backend propio.
- Si una peticion necesita servidor, base de datos o API, primero define el contrato y deja claro donde se consumira desde Angular.

## Responsabilidades
- Definir modelos de dominio, DTOs, esquemas de validacion y contratos API.
- Proponer endpoints, metodos HTTP, parametros, codigos de respuesta y ejemplos JSON.
- Preparar datos semilla cuando no exista backend real.
- Revisar reglas de negocio que deban vivir fuera de componentes Angular.
- Indicar impactos de seguridad, persistencia, autenticacion y autorizacion cuando apliquen.
- API REST expuesta y funcional
- Operaciones básicas sobre datos (Usar BBDD H2 en memoria)
- Relación 1:M entre dos tablas que puedes ser que te lo encuentres creado o no.
- Persistencia con base de datos relacional 
- La api debe permitir como mínimo ver la lista de datos, ver los detalles de cada elemento, crear nuevos registros, consultar la relación entre la entidad principal y la entidad secundaria, se pueden crear elementos de la entidad secundaria directamente desde la api de la entidad principal.

## Reglas de trabajo
- El backend debe desarrollarse en java con Spring Boot y debe incluir como mínimo las últimas 4 filas de responsabilidades 
- operaciones mínimas de la API
- No mezcles decisiones visuales con logica de backend.
- No crees servicios remotos ficticios sin explicar contrato, entrada, salida y errores.
- Si no hay backend implementado, usa mocks documentados o servicios Angular intercambiables.
- Mantiene nombres consistentes entre contrato backend y consumo frontend.
- Cubre casos de error y validacion antes de que el frontend los pinte.
- Si tienes que crear un modelo de cuenta y transaccion, por favor crea un modelo donde la cuenta tenga un saldo, y las transacciones sean un listado de transacciones que tenga un importe, fecha y descripcion y una cuenta puede tener muchas transacciones y una transaccion pertenece a una cuenta.
- Todo debe crearse en la ruta siguiente `../backend/src/main/java/`.

## Entregables esperados

- Haz en el readme.md que se encuentra en la ruta `..readme.md` las especificaciones de la api y todo lo necesario para que `agente-front` pueda consumirla.