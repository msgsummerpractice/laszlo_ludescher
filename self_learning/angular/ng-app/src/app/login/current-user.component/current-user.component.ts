import { CommonModule } from '@angular/common';
import {
  Component,
  Directive,
  inject,
  ViewContainerRef,
  TemplateRef,
  input,
  effect,
  Pipe,
  PipeTransform,
} from '@angular/core';
import { AuthService } from '../auth.service/auth.service';
import { GreetPipe } from '../greet.pipe';

@Directive({
  selector: '[appIfAuthenticated]',
})
export class IfAuthenticatedDirective {
  private readonly _viewContainerRef = inject(ViewContainerRef);
  private readonly _templateRef = inject(TemplateRef);

  authService: AuthService = inject(AuthService);

  constructor() {
    effect(() => {
      this._viewContainerRef.clear();

      if (this.authService.isAuthenticated()) {
        this._viewContainerRef.createEmbeddedView(this._templateRef);
      }
    });
  }
}

@Component({
  selector: 'current-user',
  imports: [CommonModule, IfAuthenticatedDirective, GreetPipe],
  templateUrl: './current-user.component.html',
})
export class CurrentUser {}
