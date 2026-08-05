import { Routes } from '@angular/router';
import { Home } from '../components/home.component/home.component';
import { NotFound } from '../components/not-found.component/not-found.component';
import { authGuard } from '../login/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: Home },
  {
    path: 'login',
    loadComponent: () => import('../login/login.component/login.component').then((m) => m.Login),
  },
  {
    path: 'favourites',
    canActivate: [authGuard],
    loadComponent: () =>
      import('../components/favourites.component/favourites.component').then(
        (m) => m.FavouritesComponent,
      ),
  },
  { path: '**', component: NotFound },
];
