import { Component, inject } from '@angular/core';
import { AuthService } from '../auth.service/auth.service';
import {
  FormControl,
  ReactiveFormsModule,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { IfAuthenticatedDirective } from '../current-user.component/current-user.component';

type LoginForm = {
  email: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'login',
  imports: [ReactiveFormsModule, IfAuthenticatedDirective],
  templateUrl: './login.component.html',
})
export class Login {
  public _authService: AuthService = inject(AuthService);
  private _router: Router = inject(Router);

  private readonly _formBuilder = inject(NonNullableFormBuilder);

  public isAuthenticated = this._authService.isAuthenticated;

  protected readonly loginFormGroup = this._formBuilder.group<LoginForm>({
    email: this._formBuilder.control('', Validators.required),
    password: this._formBuilder.control('', Validators.required),
  });

  onFormSubmit() {
    if (this.loginFormGroup.valid) {
      const { email, password } = this.loginFormGroup.getRawValue();
      this._authService.login(email, password).subscribe({
        next: () => {
          this._router.navigate(['home']);
        },
        error: (err) => {
          alert('Invalid email or password');
        },
      });
    }
  }
  onLogout() {
    this._authService.logout();
  }
}
