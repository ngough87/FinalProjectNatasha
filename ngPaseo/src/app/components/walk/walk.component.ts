import { WalkCategoryService } from './../../services/walk-category.service';
import { Address } from './../../models/address';
import { WalkCategory } from './../../models/walk-category';
import { AddressService } from './../../services/address.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Walk } from 'src/app/models/walk';

import { AuthService } from 'src/app/services/auth.service';
import { WalkLocationService } from 'src/app/services/walk-location.service'
import { UserService } from 'src/app/services/user.service';
import { WalkService } from 'src/app/services/walk.service';
import { WalkType } from 'src/app/models/walk-type';
import { WalkLocation } from 'src/app/models/walk-location';
import { WalkTypeService } from 'src/app/services/walk-type.service';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-walk',
  templateUrl: './walk.component.html',
  styleUrls: ['./walk.component.css'],
})
export class WalkComponent implements OnInit{
  walk: Walk = new Walk();
  walks: Walk[] = [];
  newWalk: Walk = new Walk();

  walkTypes: WalkType[] =  []
  walkCategories : WalkCategory[] = [];
  walkLocations : WalkLocation [] = [];
  addLocation: boolean = false;
  addAddress: boolean = false;



  newLocation : WalkLocation | null = null;
  newAddress: Address | null = null;
  constructor(
    private auth: AuthService,
    private router: Router,
    private userService: UserService,
    private addressService: AddressService,
    private walkService: WalkService,
    private walkLocationService: WalkLocationService,
    private walkCatService : WalkCategoryService,
    private walkTypeService : WalkTypeService

  ) {}
  ngOnInit(): void {
    this.reload();
    this.loadCategories();
    this.loadTypes();
    this.loadLocation();
  }

  reload(): void {
    this.walkService.index().subscribe({
      next: (data) => {
        this.walks = data;
      },
      error: (err) => {
        console.error('Walk.reload(): error loading walk');
        console.error(err);
      },
    });
  }

createNewWalk(walk :Walk){
  walk.user = new User();
  walk.user.id = Number(localStorage.getItem("currentUserId"));

  this.walkService.create(walk).subscribe({
    next:(data) =>{
      console.log(data);

    }
  })
}

loadCategories(){

  this.walkCatService.index().subscribe({

next: (data) =>{
  this.walkCategories = data;
}

  })


}

loadLocation(){

  this.walkLocationService.index().subscribe({

next: (data) =>{
  this.walkLocations = data;
}

  })


}


loadTypes(){

  this.walkTypeService.index().subscribe({

next: (data:WalkType[]) =>{
  this.walkTypes = data;
}

  });


}


  showAddLocation(){
    this.addLocation = true;
    this.newLocation = new WalkLocation();
  }

  createNewLocation(newLocation : WalkLocation):void{
    this.walkLocationService.create(newLocation).subscribe({
      next: (data) => {
        this.reload();
      },
      error: (fail) => {
        console.error(fail);

    },
  });
  }

  showAddAddress() {

  this.addAddress=true;
  this.newAddress= new Address;


  }

  // createLocation(walkLocation : string){

  updateWalk(walk: Walk) {
    this.walkService.update(walk).subscribe({
      next: (updateWalk) => {
        this.reload();
      },
      error: (fail) => {
        console.error('WalkComponent.updateWalk: error updating');
        console.error(fail);
      },
    });
  }

  addWalk(walk: Walk) {
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


}
