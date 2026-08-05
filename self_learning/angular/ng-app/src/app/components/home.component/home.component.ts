import { Component } from '@angular/core';
import { ImageViewerComponent } from '../image-viewer.component';

@Component({
  selector: 'app-home',
  imports: [ImageViewerComponent],
  templateUrl: './home.component.html',
})
export class Home {}
