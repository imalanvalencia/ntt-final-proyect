# 🏦 Banco Online - API REST Backend (Spring Boot)

Esta es la documentación del contrato y especificación de la API REST del backend de nuestra simulación de banco online. La aplicación se ejecuta por defecto en `http://localhost:8080` y utiliza una base de datos en memoria **H2** (consola accesible en `/h2-console` con usuario `sa` y sin contraseña).

---

## 🚀 Endpoints de la API

### 1. Gestión de Clientes (`/api/clientes`)

#### **Listar todos los clientes**
*   **Método:** `GET`
*   **Ruta:** `/api/clientes`
*   **Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "username": "alan.smith",
        "dni": "12345678A",
        "email": "alan.smith@example.com",
        "nombre": "Alan",
        "apellidos": "Smith",
        "telefono": "600112233",
        "fechaRegistro": "2026-05-27T12:00:00"
      }
    ]
    ```

#### **Ver detalle de un cliente**
*   **Método:** `GET`
*   **Ruta:** `/api/clientes/{id}`
*   **Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "username": "alan.smith",
      "dni": "12345678A",
      "email": "alan.smith@example.com",
      "nombre": "Alan",
      "apellidos": "Smith",
      "telefono": "600112233",
      "fechaRegistro": "2026-05-27T12:00:00"
    }
    ```
*   **Respuesta (400 Bad Request) - Cliente no encontrado:**
    ```json
    {
      "timestamp": "2026-05-27T12:05:00",
      "status": 400,
      "error": "Bad Request",
      "message": "Cliente no encontrado con id: 999"
    }
    ```

#### **Registrar nuevo cliente**
*   **Método:** `POST`
*   **Ruta:** `/api/clientes`
*   **Cuerpo de la Petición (Request Body):**
    ```json
    {
      "username": "jose.perez",
      "password": "mi_password_segura",
      "dni": "45678901Z",
      "email": "jose.perez@example.com",
      "nombre": "José",
      "apellidos": "Pérez",
      "telefono": "655443322"
    }
    ```
*   **Respuesta (201 Created):**
    ```json
    {
      "id": 4,
      "username": "jose.perez",
      "dni": "45678901Z",
      "email": "jose.perez@example.com",
      "nombre": "José",
      "apellidos": "Pérez",
      "telefono": "655443322",
      "fechaRegistro": "2026-05-27T12:10:00"
    }
    ```
*   **Respuesta (400 Bad Request) - DNI/Email/Username Duplicado:**
    ```json
    {
      "timestamp": "2026-05-27T12:12:00",
      "status": 400,
      "error": "Bad Request",
      "message": "Ya existe un cliente registrado con el DNI: 45678901Z"
    }
    ```

#### **Listar las cuentas de un cliente**
*   **Método:** `GET`
*   **Ruta:** `/api/clientes/{id}/cuentas`
*   **Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "numeroCuenta": "ES123456789012345678",
        "clienteId": 1,
        "clienteNombre": "Alan Smith",
        "saldo": 1500.50
      }
    ]
    ```

---

### 2. Gestión de Cuentas (`/api/cuentas`)

#### **Listar todas las cuentas**
*   **Método:** `GET`
*   **Ruta:** `/api/cuentas`
*   **Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "numeroCuenta": "ES123456789012345678",
        "clienteId": 1,
        "clienteNombre": "Alan Smith",
        "saldo": 1500.50
      }
    ]
    ```

#### **Crear nueva cuenta para un cliente**
*   **Método:** `POST`
*   **Ruta:** `/api/cuentas`
*   **Cuerpo de la Petición:**
    ```json
    {
      "clienteId": 1
    }
    ```
*   **Respuesta (201 Created):**
    *   *Nota: El sistema genera automáticamente el número de cuenta único con formato español (prefijo ES seguido de 18 dígitos aleatorios) y saldo inicial 0.00.*
    ```json
    {
      "id": 5,
      "numeroCuenta": "ES839104726481029384",
      "clienteId": 1,
      "clienteNombre": "Alan Smith",
      "saldo": 0.00
    }
    ```

#### **Ver detalle de una cuenta (Saldo)**
*   **Método:** `GET`
*   **Ruta:** `/api/cuentas/{id}`
*   **Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "numeroCuenta": "ES123456789012345678",
      "clienteId": 1,
      "clienteNombre": "Alan Smith",
      "saldo": 1500.50
    }
    ```

---

### 3. Operaciones Básicas y Movimientos (`/api/cuentas/{id}/transacciones`)

#### **Registrar un ingreso o retiro (Movimiento)**
*   **Método:** `POST`
*   **Ruta:** `/api/cuentas/{id}/transacciones`
*   **Cuerpo de la Petición:**
    *   *Nota: `tipo` debe ser "INGRESO" o "RETIRO". El `monto` siempre debe ser un número positivo.*
    ```json
    {
      "tipo": "RETIRO",
      "monto": 100.00,
      "concepto": "Retiro en Cajero Automático",
      "categoria": "Efectivo"
    }
    ```
*   **Respuesta (210 Created) - Transacción Registrada:**
    *   *Nota: En la respuesta y base de datos, la cantidad del RETIRO figurará con signo negativo (-100.00) y la del INGRESO con signo positivo (+100.00) respetando los constraints del esquema SQL.*
    ```json
    {
      "id": 6,
      "tipo": "RETIRO",
      "concepto": "Retiro en Cajero Automático",
      "cantidad": -100.00,
      "fecha": "2026-05-27T12:20:00",
      "categoria": "Efectivo",
      "cuentaId": 1
    }
    ```
*   **Respuesta (400 Bad Request) - Saldo Insuficiente:**
    ```json
    {
      "timestamp": "2026-05-27T12:21:00",
      "status": 400,
      "error": "Bad Request",
      "message": "Saldo insuficiente en la cuenta. Saldo actual: 50.00"
    }
    ```

#### **Listar movimientos de una cuenta**
*   **Método:** `GET`
*   **Ruta:** `/api/cuentas/{id}/transacciones`
*   **Respuesta (200 OK - Ordenados por fecha de más reciente a más antiguo):**
    ```json
    [
      {
        "id": 3,
        "tipo": "RETIRO",
        "concepto": "Pago internet",
        "cantidad": -49.50,
        "fecha": "2026-05-26T15:30:00",
        "categoria": "Servicios",
        "cuentaId": 1
      },
      {
        "id": 2,
        "tipo": "RETIRO",
        "concepto": "Compra supermercado",
        "cantidad": -50.00,
        "fecha": "2026-05-24T18:00:00",
        "categoria": "Alimentación",
        "cuentaId": 1
      },
      {
        "id": 1,
        "tipo": "INGRESO",
        "concepto": "Nómina mensual",
        "cantidad": 2000.00,
        "fecha": "2026-05-22T09:00:00",
        "categoria": "Nómina",
        "cuentaId": 1
      }
    ]
    ```

---

## 🛠️ Instrucciones de Ejecución local del Backend

Para compilar y arrancar este backend de forma local, utiliza los siguientes comandos Maven estándar en la carpeta `./backend`:

1. **Compilar y pasar los tests:**
   ```bash
   mvn clean test
   ```

2. **Arrancar el servidor de desarrollo:**
   ```bash
   mvn spring-boot:run
   ```

El backend se levantará en el puerto **`8080`** y habilitará el CORS para que el `agente-front` pueda consumirlo directamente desde su aplicación Angular local.