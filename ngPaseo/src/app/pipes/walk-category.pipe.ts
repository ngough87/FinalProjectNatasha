import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'walkCategory'
})
export class WalkCategoryPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
