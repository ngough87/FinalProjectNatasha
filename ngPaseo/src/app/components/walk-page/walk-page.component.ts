import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Walk } from 'src/app/models/walk';
import { WalkCategory } from 'src/app/models/walk-category';
import { WalkLocation } from 'src/app/models/walk-location';
import { WalkType } from 'src/app/models/walk-type';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { WalkCategoryService } from 'src/app/services/walk-category.service';
import { WalkLocationService } from 'src/app/services/walk-location.service';
import { WalkTypeService } from 'src/app/services/walk-type.service';
import { WalkService } from 'src/app/services/walk.service';

@Component({
  selector: 'app-walk-page',
  templateUrl: './walk-page.component.html',
  styleUrls: ['./walk-page.component.css'],
})
export class WalkPageComponent {
  walk: Walk = new Walk();
  walkTypes: WalkType[] = [];
  walkCategories: WalkCategory[] = [];
  walkLocations: WalkLocation[] = [];
  loggedInUser: User = new User();

  constructor(
    private auth: AuthService,
    private router: Router,
    private userService: UserService,
    private addressService: AddressService,
    private walkService: WalkService,
    private walkLocationService: WalkLocationService,
    private walkCatService: WalkCategoryService,
    private walkTypeService: WalkTypeService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.reload();
    this.loadCategories();
    this.loadTypes();
    this.loadLocation();
    this.getLoggedInUser();
  }

  reload(): void {
    let idString = this.route.snapshot.paramMap.get('id');

    if (idString) {
      let walkId = +idString;
      if (!isNaN(walkId)) {
        this.walkService.getById(+idString).subscribe({
          next: (data) => {
            this.walk = data;
          },
          error: (err) => {
            console.error('Walk.reload(): error loading walk');
            console.error(err);
          },
        });
      }
    } else {
      this.router.navigateByUrl('invalidWalkId');
    }
  }

  loadCategories() {
    this.walkCatService.index().subscribe({
      next: (data) => {
        this.walkCategories = data;
      },
    });
  }

  loadLocation() {
    this.walkLocationService.index().subscribe({
      next: (data) => {
        this.walkLocations = data;
      },
    });
  }

  loadTypes() {
    this.walkTypeService.index().subscribe({
      next: (data: WalkType[]) => {
        this.walkTypes = data;
      },
    });
  }

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

  return() {
  this.router.navigateByUrl('/home');
  }

  getLoggedInUser() {
    this.auth.getLoggedInUser().subscribe({
      next: (data) => {
        this.loggedInUser = data;
      },
    });
  }

  checkAuth(): boolean{
   let allowed: boolean = false;
  if( this.loggedInUser.role === 'ADMIN' || this.loggedInUser.username === this.walk.user.username){
    allowed = true;
  }
  return allowed;
  }

  delete(id: number){
    this.walkService.destroy(id).subscribe({
      next:(data) =>{
      this.router.navigateByUrl('/profile');

      },
      error: (error) =>{
        console.error('WalkPageComponent.deleteWalk: error deleting');
        console.error(error);
      }
    });
  }
}
