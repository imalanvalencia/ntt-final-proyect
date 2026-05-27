# 🏦 Banco Online Fullstack - Guía del Proyecto

Este repositorio contiene la simulación completa de una plataforma de **Banco Online** integrada. Se compone de un front-end desarrollado en **Angular** (interfaz de cajero inteligente/dashboard) y un back-end en **Spring Boot** (servicios de persistencia y API REST con base de datos H2 en memoria).

---

## 📂 Estructura del Repositorio

*   **`./frontend`**: Aplicación web desarrollada con **Angular 21**. Implementa componentes reactivos Standalone, gestión de estado mediante *Angular Signals*, e interactúa con el backend mediante Observables y servicios.
*   **`./backend`**: Servicio REST en **Spring Boot** que expone la lógica de negocio, validaciones y acceso a datos usando *Spring Data JPA* y *H2 Database*.
*   **`./documentacion`**: Diagramas y PDF de especificaciones de diseño.
*   **`ddl.sql`**: Definición de la estructura de la base de datos (DDL).

---

## 🛠️ Requisitos Previos

1.  **Node.js** (v20 o superior recomendado).
2.  **Java JDK 17 o superior** (Se ha probado con Java 24).
3.  **Configurar `JAVA_HOME`**: Asegúrate de tener configurada la variable de entorno `JAVA_HOME` apuntando a tu instalación de JDK. En Windows, normalmente se ubica en:
    ```powershell
    # Temporal en PowerShell:
    $env:JAVA_HOME = "C:\Program Files\Java\jdk-24"
    ```

---

## 🚀 Arranque Rápido (Recomendado)

Hemos configurado un orquestador en la raíz del proyecto para que puedas levantar tanto el frontend como el backend de forma simultánea con un único comando utilizando `concurrently`.

1.  **Instalar dependencias globales y de proyectos:**
    Desde la raíz del repositorio, ejecuta:
    ```bash
    npm install
    ```
    *(Este comando instalará la herramienta de concurrencia en la raíz e instalará automáticamente los módulos de Node del frontend).*

2.  **Arrancar ambos servidores en desarrollo:**
    Desde la raíz del repositorio, ejecuta:
    ```bash
    npm run dev
    ```
    Este comando lanzará en paralelo:
    *   El **Backend (Spring Boot)** en `http://localhost:8080` (utilizando el Maven Wrapper local).
    *   El **Frontend (Angular)** en `http://localhost:4200` (utilizando el Angular CLI).

---

## 💻 Ejecución Manual por Carpetas

Si prefieres ejecutar cada servicio de forma individual en terminales separadas, sigue estas instrucciones:

### 1. Iniciar el Backend (Spring Boot)
1. Ve al directorio del backend:
   ```bash
   cd backend
   ```
2. Ejecuta el servidor usando el Maven Wrapper incluido:
   *   **Windows (PowerShell):**
       ```powershell
       .\mvnw.cmd spring-boot:run
       ```
   *   **Linux/macOS:**
       ```bash
       ./mvnw spring-boot:run
       ```
3. El backend estará disponible en `http://localhost:8080`.

### 2. Iniciar el Frontend (Angular)
1. Ve al directorio del frontend:
   ```bash
   cd frontend
   ```
2. Instala las dependencias (si no lo has hecho antes):
   ```bash
   npm install
   ```
3. Arranca el servidor de desarrollo de Angular:
   ```bash
   npm start
   ```
4. Abre tu navegador en `http://localhost:4200`.

---

## 🗄️ Base de Datos en Memoria (H2)

El backend utiliza una base de datos **H2 en memoria** que se reinicializa y se pobla con datos de prueba automáticamente en cada arranque utilizando los scripts `./backend/src/main/resources/schema.sql` y `data.sql`.

*   **Consola de Administración H2:** `http://localhost:8080/h2-console`
*   **JDBC URL:** `jdbc:h2:mem:bancodb`
*   **Usuario:** `sa`
*   **Contraseña:** *(Vacío, sin contraseña)*

### Datos de Prueba Pre-cargados
*   **Usuario Principal (ATM):**
    *   **ID:** `1`
    *   **Nombre:** Alan Smith
    *   **Username:** `alan.smith`
    *   **DNI:** `12345678A`
    *   **Password:** `password123`
*   **Cuentas asociadas a Alan:**
    *   `ES123456789012345678` (Saldo: 1500.50 €)
    *   `ES987654321098765432` (Saldo: 50.00 €)

---

## 📡 Endpoints de la API REST

### 1. Clientes (`/api/clientes`)
| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/clientes` | Obtiene una lista de todos los clientes. |
| **GET** | `/api/clientes/{id}` | Obtiene la información detallada de un cliente. |
| **POST** | `/api/clientes` | Registra un nuevo cliente (recibe un DTO de cliente). |
| **GET** | `/api/clientes/{id}/cuentas` | Obtiene todas las cuentas bancarias asociadas a un cliente. |

### 2. Cuentas (`/api/cuentas`)
| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/cuentas` | Obtiene la lista completa de todas las cuentas registradas. |
| **GET** | `/api/cuentas/{id}` | Obtiene los detalles de una cuenta (incluido el saldo). |
| **POST** | `/api/cuentas` | Crea una nueva cuenta bancaria para un cliente (saldo inicial: `0.00`). |

### 3. Transacciones y Movimientos (`/api/cuentas/{id}/transacciones`)
| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/cuentas/{id}/transacciones` | Lista los movimientos de una cuenta (ordenados de más recientes a antiguos). |
| **POST** | `/api/cuentas/{id}/transacciones` | Registra una transacción (`INGRESO` o `RETIRO`) en la cuenta especificada. |

---

## 🧠 Flujo de la Aplicación (Smart ATM)

1.  **Acceso Seguro (PIN):** El frontend presenta una pantalla de inicio simulando un cajero automático. Cualquier PIN de 4 dígitos cargará automáticamente al usuario por defecto `alan.smith` (ID 1) y sus cuentas desde el backend.
2.  **Dashboard:** Muestra las cuentas del usuario y su saldo en tiempo real. Puedes alternar entre cuentas para visualizar su historial de movimientos.
3.  **Depósito y Retiro:** Permite usar un teclado numérico digital para ingresar o retirar efectivo de la cuenta seleccionada. El saldo se actualiza de inmediato mediante llamadas al backend, validando que no haya saldo negativo.
4.  **Asistente IA Integrado:** Un chat interactivo simulado en la barra lateral que responde a tus preguntas financieras y de seguridad.