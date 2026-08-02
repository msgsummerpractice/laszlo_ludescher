import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'greetPipe' })
export class GreetPipe implements PipeTransform {
  transform(username: string): string {
    return `Welcome, ${username}!`;
  }
}
