import { User } from './../../models/user';
import { AuthService } from './../../services/auth.service';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Walk } from 'src/app/models/walk';
import { Address } from 'src/app/models/address';
import { WalkCategory } from 'src/app/models/walk-category';
import { WalkLocation } from 'src/app/models/walk-location';
import { WalkType } from 'src/app/models/walk-type';
import { AddressService } from 'src/app/services/address.service';
import { UserService } from 'src/app/services/user.service';
import { WalkCategoryService } from 'src/app/services/walk-category.service';
import { WalkLocationService } from 'src/app/services/walk-location.service';
import { WalkTypeService } from 'src/app/services/walk-type.service';
import { WalkService } from 'src/app/services/walk.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);



  selected : Walk | null = null;
  walks: Walk[] = [];
  searchWalk: Walk = new Walk();
  users: User[] = [];
  usersSearchResults: User[] = [];
  createNewWalk : boolean = false;
  walkTypes: WalkType[] = [];
  walkCategories: WalkCategory[] = [];
  walkLocations: WalkLocation[] = [];
  displayWalks: Walk[] | null = null;
  results: Walk[] = [];
  keyword: String = "";
  loggedInUser: User = new User();

  newLocation: WalkLocation | null = null;
  newAddress: Address | null = null;
  constructor(
    private auth: AuthService,
    private router: Router,
    private userService: UserService,
    private addressService: AddressService,
    private walkService: WalkService,
    private walkLocationService: WalkLocationService,
    private walkCatService: WalkCategoryService,
    private walkTypeService: WalkTypeService,

  ) {

  }


  ngOnInit(): void {
    this.getLoggedInUser();
    this.loadCategories();
    this.loadTypes();
    this.loadLocation();
    this.reload();
    this.checkLogin();


  }


  onSubmitUserSearch(keyword: String){
    this.userService.searchForUser(keyword).subscribe({
      next: (data : User[]) => {
        this.usersSearchResults = data;

      },
      error: (error) =>{
        console.error("HomeComponent search() failed");
        console.error(error);
      }
    })
  }


search(searchWalk : Walk){
this.walkService.search(searchWalk).subscribe({
  next: (data) => {
    this.results = data;
  },
error: (error) =>{
  console.error("HomeComponent search() failed");
  console.error(error);
}
});

}
reload(){
  this.walkService.index().subscribe({
    next: (data) => {
      this.walks = data;
      this.displayWalks = this.walks;
    },
    error: (error) =>{
      console.error("HomeComponent reload error");
      console.error(error);
    }

  });
}

  checkLogin(){
    return this.auth.checkLogin();
  }

  registerRedir() {
    this.router.navigateByUrl('/register');
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
  getLoggedInUser(){
    this.auth.getLoggedInUser().subscribe({
      next: (data) => {
        this.loggedInUser = data;

      }
    })
  }
  updateUser(user: User){
    console.log(user.id)
    this.router.navigateByUrl('/settings/' + user.id);
  }

}
