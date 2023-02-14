import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'walkGender'
})
export class WalkGenderPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
