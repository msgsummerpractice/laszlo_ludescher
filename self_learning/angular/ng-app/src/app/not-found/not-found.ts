import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { MatToolbarRow } from '@angular/material/toolbar';

@Component({
  selector: 'not-found',
  imports: [MatToolbarRow, MatIcon],
  templateUrl: './not-found.html',
  styleUrl: './not-found.css',
  standalone: true,
})
export class NotFound {}
