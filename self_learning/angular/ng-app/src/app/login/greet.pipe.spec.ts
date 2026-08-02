import { GreetPipe } from './greet.pipe';

describe('GreetPipePipe', () => {
  it('create an instance', () => {
    const pipe = new GreetPipe();
    expect(pipe).toBeTruthy();
  });
});
