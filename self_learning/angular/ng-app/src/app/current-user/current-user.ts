import { CommonModule } from '@angular/common';
import {
  Component,
  Directive,
  inject,
  ViewContainerRef,
  TemplateRef,
  input,
  effect,
} from '@angular/core';

@Directive({
  selector: '[appIfAuthenticated]',
})
export class IfAuthenticatedDirective {
  private readonly _viewContainerRef = inject(ViewContainerRef);
  private readonly _templateRef = inject(TemplateRef);

  appIfAuthenticated = input<boolean>(false);

  constructor() {
    effect(() => {
      this._viewContainerRef.clear();

      if (this.appIfAuthenticated()) {
        this._viewContainerRef.createEmbeddedView(this._templateRef);
      }
    });
  }
}

@Component({
  selector: 'current-user',
  imports: [CommonModule, IfAuthenticatedDirective],
  templateUrl: './current-user.html',
  styleUrl: './current-user.css',
})
export class CurrentUser {
  isAuthenticated = true;
}
