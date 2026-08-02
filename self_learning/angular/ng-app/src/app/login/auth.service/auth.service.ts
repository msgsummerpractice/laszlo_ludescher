import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

type LoginRequest = {
  email: string;
  password: string;
};

type LoginResponse = {
  accessToken: string;
};

@Injectable({ providedIn: 'root' })
export class AuthService {
  isAuthenticated = signal(false);
  private http = inject(HttpClient);

  login(email: string, password: string) {
    const body: LoginRequest = { email, password };

    return this.http.post<LoginResponse>('http://localhost:8081/login', body).pipe(
      tap({
        next: (response) => {
          localStorage.setItem('token', response.accessToken);
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
    localStorage.removeItem('token');
    this.isAuthenticated.set(false);
  }
}
