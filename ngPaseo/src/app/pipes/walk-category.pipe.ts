import { Pipe, PipeTransform } from '@angular/core';
import { Walk } from '../models/walk';

@Pipe({
  name: 'walkCategory'
})
export class WalkCategoryPipe implements PipeTransform {

  transform(walks: Walk[], walkTypeId:number): Walk[] {
   let filteredWalks : Walk[] |null = []

    return filteredWalks;


  }

}
