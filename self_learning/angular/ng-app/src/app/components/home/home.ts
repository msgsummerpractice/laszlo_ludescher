import { Component } from '@angular/core';
import { ImageViewerComponent } from '../image-viewer.component';
import { MatCard } from '@angular/material/card';

@Component({
  selector: 'app-home',
  imports: [ImageViewerComponent, MatCard],
  templateUrl: './home.html',
})
export class Home {}
