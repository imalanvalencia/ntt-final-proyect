import { Injectable, signal } from '@angular/core';
import { Cliente } from '../models/bank.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Global auth state using Signals
  readonly currentUser = signal<Cliente | null>(null);
  readonly isAuthenticated = signal<boolean>(false);

  loginWithPin(pin: string): boolean {
    // For ATM access, any 4-digit PIN is accepted.
    // We log in the default user "Alan Smith" (ID 1) matching backend database.
    if (pin.length === 4) {
      this.currentUser.set({
        id: 1,
        username: 'alan.smith',
        dni: '12345678A',
        email: 'alan.smith@example.com',
        nombre: 'Alan',
        apellidos: 'Smith',
        telefono: '600112233'
      });
      this.isAuthenticated.set(true);
      return true;
    }
    return false;
  }

  logout(): void {
    this.currentUser.set(null);
    this.isAuthenticated.set(false);
  }
}
