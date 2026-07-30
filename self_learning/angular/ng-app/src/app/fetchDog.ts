import { of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { interval } from 'rxjs';
import { sample } from 'rxjs/operators';
import { fromFetch } from 'rxjs/fetch';

type DogApiResponse = {
  message: string;
  status: string;
};

const button: HTMLButtonElement = document.getElementById('showDogBtn') as HTMLButtonElement;
button.addEventListener('click', showDog);

async function showDog(): Promise<void> {
  button.innerHTML = 'halo';
  let image: HTMLImageElement = document.getElementById('image') as HTMLImageElement;
  try {
    const data$ = fromFetch('https://dog.ceo/api/breeds/image/random').pipe(
      map((response) => {
        if (response.ok) {
          return response.json();
        } else {
          return of({ error: true, message: `Error ${response.status}` });
        }
      }),
    );

    data$.subscribe({
      next: (result) => console.log(result),
      complete: () => console.log('done'),
    });
  } catch (error) {
    console.error('Failed to fetch dog image:', error);
  }
}
