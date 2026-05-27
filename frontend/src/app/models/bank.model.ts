export interface Cliente {
  id: number;
  username: string;
  dni: string;
  email: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  fechaRegistro?: string;
}

export interface Cuenta {
  id: number;
  numeroCuenta: string;
  clienteId: number;
  clienteNombre: string;
  saldo: number;
}

export interface Transaccion {
  id?: number;
  tipo: 'INGRESO' | 'RETIRO';
  concepto: string;
  monto?: number; // Usado en la petición POST del cuerpo
  cantidad?: number; // Devuelto en la respuesta
  fecha?: string;
  categoria: string;
  cuentaId?: number;
}
