import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'walkLocation'
})
export class WalkLocationPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
