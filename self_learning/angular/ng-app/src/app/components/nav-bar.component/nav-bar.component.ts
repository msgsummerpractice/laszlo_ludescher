import { Component } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatToolbarRow } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';
import { CurrentUser } from '../../login/current-user.component/current-user.component';

@Component({
  selector: 'app-nav-bar',
  imports: [MatButton, MatToolbarRow, MatIcon, CurrentUser, RouterLink],
  templateUrl: './nav-bar.component.html',
})
export class NavBar {}
