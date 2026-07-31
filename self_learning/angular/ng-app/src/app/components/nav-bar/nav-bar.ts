import { Component } from '@angular/core';
import { Login } from '../login/login';
import { MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatToolbarRow } from '@angular/material/toolbar';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CurrentUser } from '../current-user/current-user';
import { NotFound } from '../not-found/not-found';
import { ImageViewerComponent } from '../image-viewer.component';

@Component({
  selector: 'app-nav-bar',
  imports: [
    Login,
    RouterOutlet,
    MatButton,
    MatToolbarRow,
    MatIcon,
    ImageViewerComponent,
    CurrentUser,
    RouterLink,
  ],
  templateUrl: './nav-bar.html',
})
export class NavBar {}
