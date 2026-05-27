import { Component, signal, inject, effect } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-acceso-seguro',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './acceso-seguro.component.html',
  styleUrls: ['./acceso-seguro.component.css']
})
export class AccesoSeguroComponent {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  // States using Signals
  readonly pin = signal<string>('');
  readonly maxPinLength = 4;
  readonly showSuccessOverlay = signal<boolean>(false);
  readonly errorMessage = signal<string>('');

  constructor() {
    // Watch PIN length to trigger authentication when 4 digits are typed
    effect(() => {
      const currentPin = this.pin();
      if (currentPin.length === this.maxPinLength) {
        this.verifyPin();
      }
    });
  }

  pressKey(num: string): void {
    if (this.pin().length < this.maxPinLength) {
      this.pin.update(p => p + num);
      this.triggerHapticFeedback();
    }
  }

  deleteKey(): void {
    if (this.pin().length > 0) {
      this.pin.update(p => p.slice(0, -1));
      this.triggerHapticFeedback();
    }
  }

  verifyPin(): void {
    const isSuccess = this.authService.loginWithPin(this.pin());
    if (isSuccess) {
      this.showSuccessOverlay.set(true);
      setTimeout(() => {
        this.router.navigate(['/dashboard']);
      }, 1500);
    } else {
      this.errorMessage.set('PIN incorrecto. Inténtelo de nuevo.');
      setTimeout(() => {
        this.pin.set('');
        this.errorMessage.set('');
      }, 1000);
    }
  }

  biometricLogin(): void {
    this.triggerHapticFeedback();
    this.showSuccessOverlay.set(true);
    // Log in default user
    this.authService.loginWithPin('1234');
    setTimeout(() => {
      this.router.navigate(['/dashboard']);
    }, 1500);
  }

  private triggerHapticFeedback(): void {
    if (typeof window !== 'undefined' && window.navigator && window.navigator.vibrate) {
      window.navigator.vibrate(10);
    }
  }
}
