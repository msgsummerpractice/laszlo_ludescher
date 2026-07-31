import { Routes } from '@angular/router';
import { Home } from '../components/home/home';
import { NotFound } from '../components/not-found/not-found';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: Home },
  { path: 'login', loadComponent: () => import('../components/login/login').then((m) => m.Login) },
  { path: '**', component: NotFound },
];
