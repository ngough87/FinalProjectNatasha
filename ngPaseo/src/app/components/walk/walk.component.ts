import { Address } from './../../models/address';
import { WalkCategory } from './../../models/walk-category';
import { AddressService } from './../../services/address.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Walk } from 'src/app/models/walk';



import { AuthService } from 'src/app/services/auth.service';

import { UserService } from 'src/app/services/user.service';
import { WalkService } from 'src/app/services/walk.service';
import { WalkType } from 'src/app/models/walk-type';

@Component({
  selector: 'app-walk',
  templateUrl: './walk.component.html',
  styleUrls: ['./walk.component.css']
})


export class WalkComponent {

  walk: Walk = new Walk();
  walks: Walk[] = [];
  newWalk: Walk = new Walk();
  walkCategories: WalkCategory = new WalkCategory();
  walkTypes: WalkType = new WalkType();

  addLocation : boolean = false;
  addAddress : boolean = false;


  newAddress: Address | null = null;
  constructor(private auth: AuthService,
    private router: Router, private userService: UserService, private addressService : AddressService,
    private walkService: WalkService){}





  reload(): void{
    this.walkService.index().subscribe({

      next: (data) =>{
        this.walks = data;
      },
      error:(err) => {
        console.error('Walk.reload(): error loading walk');
        console.error(err);
      }
      }
    );

  }

  createAddress(address : Address){}

  // createLocation(walkLocation : string){

  }


    updateWalk(walk: Walk){
      this.walkService.update(walk).subscribe({

        next: (updateWalk) => {
       this.reload();

        },
        error: (fail) =>{
          console.error('WalkComponent.updateWalk: error updating');
          console.error(fail);

        }





      });
    }


    addWalk(walk: Walk)  {
      this.walkService.create(walk).subscribe({
        next: (data) => {
        this.newWalk = new Walk();
          this.reload();
        },
        error: (err) => {
          console.error('WalkComponent.addWalk(): error creating walk:');
          console.error(err);
        },
      });

    }

  updateAddres(){

   return
  }





