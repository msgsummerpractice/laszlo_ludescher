import { Component, inject } from '@angular/core';
import { of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { interval } from 'rxjs';
import { sample } from 'rxjs/operators';
import { fromFetch } from 'rxjs/fetch';
import { signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'image-viewer',
  template: `
    <div class="flex flex-col justify-center py-15">
      <button
        id="showDogBtn"
        (click)="showDogImage()"
        class="bg-slate-300 border border-olive-400 shadow-xl/30 rounded-xl min-w"
      >
        Show me a dog
      </button>
    </div>
    @if (imageUrl()) {
      <div
        class="w-76 h-80 bg-slate-300 border-olive-400 shadow-xl/30 rounded-xl min-w p-4 flex items-center justify-center"
      >
        <img id="image" [src]="imageUrl()" class="w-72 h-72 object-cover rounded-lg" />
      </div>
    }
  `,
})
export class ImageViewerComponent {
  imageUrl = signal<string | null>(null);
  private http = inject(HttpClient);
  showDogImage() {
    type DogApiResponse = {
      message: string;
      status: string;
    };

    this.http
      .get<DogApiResponse>('https://dog.ceo/api/breeds/image/random', {
        responseType: 'json',
      })
      .subscribe((response) => {
        this.imageUrl.set(response.message);
      });
  }
}
