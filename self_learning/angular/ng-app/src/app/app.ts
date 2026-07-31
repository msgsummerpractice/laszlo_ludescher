import { Component, signal } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatToolbar, MatToolbarRow } from '@angular/material/toolbar';
import { RouterOutlet } from '@angular/router';
import { ImageViewerComponent } from './components/image-viewer.component';
import { HttpClient } from '@angular/common/http';
import { Login } from './components/login/login';
import { CurrentUser } from './components/current-user/current-user';
import { Home } from './components/home/home';
import { NavBar } from './components/nav-bar/nav-bar';
import { NotFound } from './components/not-found/not-found';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavBar],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('ng-app');
}
