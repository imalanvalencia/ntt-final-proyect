import { Component, OnInit, signal, inject, computed } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { BankService } from '../../services/bank.service';
import { Cliente, Cuenta, Transaccion } from '../../models/bank.model';

interface ChatMessage {
  text: string;
  sender: 'ai' | 'user';
}

@Component({
  selector: 'app-smart-atm-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './smart-atm-dashboard.component.html',
  styleUrls: ['./smart-atm-dashboard.component.css']
})
export class SmartAtmDashboardComponent implements OnInit {
  private readonly authService = inject(AuthService);
  private readonly bankService = inject(BankService);
  private readonly router = inject(Router);

  // States using Signals
  readonly currentUser = signal<Cliente | null>(null);
  readonly accounts = signal<Cuenta[]>([]);
  readonly selectedAccount = signal<Cuenta | null>(null);
  readonly transactions = signal<Transaccion[]>([]);
  readonly operationAmount = signal<number>(0);
  
  // AI Chat Assistant State
  readonly assistantQuery = signal<string>('');
  readonly chatHistory = signal<ChatMessage[]>([
    { text: 'Hola Alan, he analizado tus gastos de esta semana. Tu gasto en alimentación ha subido un 12%. ¿Quieres que ajuste tu presupuesto?', sender: 'ai' },
    { text: 'Parece que el retiro de 300€ no coincide con tu patrón habitual. ¿Confirmas esta operación?', sender: 'ai' }
  ]);

  ngOnInit(): void {
    // Auth check
    const user = this.authService.currentUser();
    if (!user) {
      this.router.navigate(['/login']);
      return;
    }
    this.currentUser.set(user);
    this.loadAccounts(user.id);
  }

  loadAccounts(clienteId: number): void {
    this.bankService.getClientAccounts(clienteId).subscribe({
      next: (accountsList) => {
        this.accounts.set(accountsList);
        if (accountsList.length > 0) {
          this.selectAccount(accountsList[0]);
        }
      },
      error: (err) => console.error('Error loading accounts:', err)
    });
  }

  selectAccount(account: Cuenta): void {
    this.selectedAccount.set(account);
    this.loadTransactions(account.id);
  }

  loadTransactions(cuentaId: number): void {
    this.bankService.getTransactions(cuentaId).subscribe({
      next: (txList) => {
        this.transactions.set(txList);
      },
      error: (err) => console.error('Error loading transactions:', err)
    });
  }

  addAmount(value: number): void {
    this.operationAmount.update(amt => amt + value);
    this.triggerHapticFeedback();
  }

  clearAmount(): void {
    this.operationAmount.set(0);
    this.triggerHapticFeedback();
  }

  executeWithdrawal(): void {
    const account = this.selectedAccount();
    const amount = this.operationAmount();
    if (!account || amount <= 0) return;

    const tx: Transaccion = {
      tipo: 'RETIRO',
      monto: amount,
      concepto: 'Retiro en Cajero Automático (ATM)',
      categoria: 'Efectivo'
    };

    this.bankService.createTransaction(account.id, tx).subscribe({
      next: () => {
        this.operationAmount.set(0);
        this.refreshAccount(account.id);
        this.triggerHapticFeedback();
      },
      error: (err) => {
        console.error('Error executing withdrawal:', err);
        alert(err.error?.message || 'Error al procesar el retiro.');
      }
    });
  }

  executeDeposit(): void {
    const account = this.selectedAccount();
    const amount = this.operationAmount();
    if (!account || amount <= 0) return;

    const tx: Transaccion = {
      tipo: 'INGRESO',
      monto: amount,
      concepto: 'Ingreso de Efectivo en ATM',
      categoria: 'Efectivo'
    };

    this.bankService.createTransaction(account.id, tx).subscribe({
      next: () => {
        this.operationAmount.set(0);
        this.refreshAccount(account.id);
        this.triggerHapticFeedback();
      },
      error: (err) => {
        console.error('Error executing deposit:', err);
        alert(err.error?.message || 'Error al procesar el depósito.');
      }
    });
  }

  private refreshAccount(cuentaId: number): void {
    this.bankService.getAccountDetails(cuentaId).subscribe({
      next: (updatedAccount) => {
        // Update selected account state and refresh transactions
        this.selectedAccount.set(updatedAccount);
        this.loadTransactions(cuentaId);
        
        // Also update the account in the accounts list signal
        this.accounts.update(list => 
          list.map(acc => acc.id === updatedAccount.id ? updatedAccount : acc)
        );
      }
    });
  }

  sendAiMessage(): void {
    const query = this.assistantQuery().trim();
    if (!query) return;

    this.chatHistory.update(history => [...history, { text: query, sender: 'user' }]);
    this.assistantQuery.set('');

    // Mock AI Response
    setTimeout(() => {
      let responseText = 'Entendido. Estoy analizando tu solicitud en tiempo real para tu banca privada.';
      if (query.toLowerCase().includes('tarjeta') || query.toLowerCase().includes('bloquear')) {
        responseText = 'Proceso de bloqueo de tarjeta iniciado. Por favor, confirma en el menú de seguridad.';
      } else if (query.toLowerCase().includes('fraude') || query.toLowerCase().includes('reportar')) {
        responseText = 'Reporte de actividad inusual registrado. El departamento de seguridad se pondrá en contacto contigo de inmediato.';
      }
      this.chatHistory.update(history => [...history, { text: responseText, sender: 'ai' }]);
    }, 1000);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  private triggerHapticFeedback(): void {
    if (typeof window !== 'undefined' && window.navigator && window.navigator.vibrate) {
      window.navigator.vibrate(10);
    }
  }
}
