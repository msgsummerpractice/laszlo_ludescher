import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

type LoginRequest = {
  email: string;
  password: string;
};

type LoginResponse = void;

@Injectable({ providedIn: 'root' })
export class AuthService {
  isAuthenticated = signal(localStorage.getItem('isLoggedIn') === 'true');
  private http = inject(HttpClient);

  login(email: string, password: string) {
    const body: LoginRequest = { email, password };

    return this.http
      .post<LoginResponse>('http://localhost:8081/login', body, { withCredentials: true })
      .pipe(
        tap({
          next: (response) => {
            localStorage.setItem('isLoggedIn', 'true');
            this.isAuthenticated.set(true);
          },
          error: (error) => {
            console.error('Login failed:', error);
            this.isAuthenticated.set(false);
          },
        }),
      );
  }

  logout() {
    localStorage.removeItem('isLoggedIn');
    this.isAuthenticated.set(false);
    this.isAuthenticated.set(false);
    this.http.post('http://localhost:8081/logout', {}, { withCredentials: true }).subscribe({
      error: (err) => console.error('Logout failed:', err),
    });
  }
}
