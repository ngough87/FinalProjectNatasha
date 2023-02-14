import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'walkType'
})
export class WalkTypePipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
