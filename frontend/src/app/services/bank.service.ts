import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cuenta, Transaccion } from '../models/bank.model';

@Injectable({
  providedIn: 'root'
})
export class BankService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api';

  // Get accounts belonging to a specific client
  getClientAccounts(clienteId: number): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(`${this.apiUrl}/clientes/${clienteId}/cuentas`);
  }

  // Get details (including current balance) of a single account
  getAccountDetails(cuentaId: number): Observable<Cuenta> {
    return this.http.get<Cuenta>(`${this.apiUrl}/cuentas/${cuentaId}`);
  }

  // Get the list of transactions for a specific account
  getTransactions(cuentaId: number): Observable<Transaccion[]> {
    return this.http.get<Transaccion[]>(`${this.apiUrl}/cuentas/${cuentaId}/transacciones`);
  }

  // Submit a transaction (deposit or withdrawal) for an account
  createTransaction(cuentaId: number, transaction: Transaccion): Observable<Transaccion> {
    return this.http.post<Transaccion>(`${this.apiUrl}/cuentas/${cuentaId}/transacciones`, transaction);
  }
}
