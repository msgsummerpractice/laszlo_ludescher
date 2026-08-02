import { Component, inject } from '@angular/core';
import { AuthService } from '../auth.service/auth.service';
import { FormGroup, FormsModule, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'login',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
})
export class Login {
  authService: AuthService = inject(AuthService);
  profileForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  submitLoginCredentials() {
    this.authService.login();
  }
}
