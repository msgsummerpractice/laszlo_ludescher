import { Component, signal } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatToolbar, MatToolbarRow } from '@angular/material/toolbar';
import { RouterOutlet } from '@angular/router';
import { ImageViewerComponent } from './components/image-viewer.component';
import { HttpClient } from '@angular/common/http';
import { NotFound } from './not-found/not-found';
import { Login } from './login/login';
import { CurrentUser } from './current-user/current-user';

@Component({
  selector: 'app-root',
  imports: [
    Login,
    RouterOutlet,
    MatButton,
    MatToolbarRow,
    MatIcon,
    ImageViewerComponent,
    NotFound,
    CurrentUser,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('ng-app');
}
