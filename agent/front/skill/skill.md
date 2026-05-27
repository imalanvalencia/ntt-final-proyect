# Agente Front

## Objetivo

Implementar y mantener la interfaz Angular del proyecto `pruebas` con componentes claros, estilos SCSS mantenibles y comportamiento verificable.

## Contexto del repositorio

- Aplicacion Angular en `../../../pruebas`.
- Version principal: Angular 21.
- Entrada: `src/main.ts`.
- Componente raiz: `src/app/app.ts`, `src/app/app.html`, `src/app/app.scss`.
- Rutas: `src/app/app.routes.ts`.
- Estilos globales: `src/styles.scss`.
- Scripts relevantes: `npm start`, `npm run build`, `npm test`.
- estilo de hosjas utiliza de este  url:
## Responsabilidades
-  Usar componentes StandAlone
- Uso de signals para manejar estado en alguna parte de la aplicación 
- Uso de formularios reactivos
- Llamada a Api Rest desde servicios angular
- Uso de Observables en los servicios
- Navegación (Rutas) básica entre vistas
- Crear y modificar componentes, rutas, plantillas y estilos.
- Implementar servicios Angular para consumir contratos definidos por `agente-back`.
- Gestionar estados de carga, exito, vacio y error.
- Cuidar accesibilidad, responsive design y consistencia visual.
- Actualizar pruebas cuando cambie el comportamiento visible.
- Método anti fraude 
## Reglas de trabajo

- Sigue patrones Angular standalone.
- Usa SCSS existente y evita estilos globales si el estilo pertenece a un componente.
- No incrustes reglas de negocio complejas en plantillas.
- Si falta backend, consume mocks o servicios intercambiables definidos junto a `agente-back`.
- Verifica con `npm run build` y, cuando proceda, `npm test`.

## Entregables esperados

- Componentes y rutas funcionales.
- Servicios tipados.
- Estilos responsive.
- Pruebas o ajustes de pruebas para el comportamiento cambiado.
