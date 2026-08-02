import { Component, inject } from '@angular/core';
import { AuthService } from '../auth.service/auth.service';
import {
  FormGroup,
  FormsModule,
  FormControl,
  ReactiveFormsModule,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';

type LoginForm = {
  email: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
})
export class Login {
  private _authService: AuthService = inject(AuthService);
  private _router: Router = inject(Router);

  private readonly _formBuilder = inject(NonNullableFormBuilder);

  protected readonly loginFormGroup = this._formBuilder.group<LoginForm>({
    email: this._formBuilder.control('', Validators.required),
    password: this._formBuilder.control('', Validators.required),
  });

  onFormSubmit() {
    if (this.loginFormGroup.valid) {
      console.log('getRawValue():', this.loginFormGroup.getRawValue());
      const { email, password } = this.loginFormGroup.getRawValue();
      this._authService.login(email, password).subscribe({
        next: () => {
          this._router.navigate(['home']);
        },
        error: (err) => {
          alert('Invalid email or password');
        },
      });
      if (this._authService.isAuthenticated()) {
      }
    }
  }
}
