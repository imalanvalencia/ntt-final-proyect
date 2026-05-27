import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () => import('./components/acceso-seguro/acceso-seguro.component').then(m => m.AccesoSeguroComponent)
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/smart-atm-dashboard/smart-atm-dashboard.component').then(m => m.SmartAtmDashboardComponent)
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];
