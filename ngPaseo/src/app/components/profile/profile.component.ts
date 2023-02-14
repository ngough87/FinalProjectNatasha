import { AuthService } from 'src/app/services/auth.service';
import { WalkService } from 'src/app/services/walk.service';
import { AddressService } from './../../services/address.service';
import { GenderService } from './../../services/gender.service';

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Walk } from 'src/app/models/walk';
import { UserService } from 'src/app/services/user.service';
import { Address } from 'src/app/models/address';
import { Gender } from 'src/app/models/gender';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
[x: string]: any;
  walks: Walk[] = [];
  user: User = new User();
  address: Address = new Address();
  userGender = new Gender();
  genders: Gender[] = [];
  displayPhotos = true;
  displayWalks = true;
  displayGroups = false;
  displayInterests = false;

  imageUrl: string = this.user.profileImageUrl;
  constructor(
    private auth: AuthService,
    private userService: UserService,
    private router: Router,
    private addressService: AddressService,
    private gender: GenderService,
    private walkService: WalkService,
    private route: ActivatedRoute ) {}

  ngOnInit() {
    this.getUser();
  }
  getUser() {
    let id: string | null = this.route.snapshot.paramMap.get('id');

    if (id && !isNaN(+id)) {
      //If ID present
      this.userService.findUser(+id).subscribe({
        next: (data) => {
          console.log(data);
          this.user = data;
          console.log(this.user);
          this.getUserWalks();
          if (!this.user.address) {
            this.user.address = new Address();
          } else {
            this.address = data.address!;
          }
          if (!this.user.gender) {
            this.user.gender = new Gender();
          } else {
            this.userGender = data.gender!;
          }

          this.imageUrl = Object.assign({}, this.user.profileImageUrl);
          console.log('Logged in');
        },
        error: (fail) => {
          this.router.navigateByUrl('/notFound');
          console.error(fail);
        },
      });
    } else {
      //If no ID present
      this.auth.getLoggedInUser().subscribe({
        next: (data) => {
          console.log(data);
          this.user = data;
          console.log(this.user);
          this.getUserWalks();
          if (!this.user.address) {
            this.user.address = new Address();
          } else {
            this.address = data.address!;
          }
          if (!this.user.gender) {
            this.user.gender = new Gender();
          } else {
            this.userGender = data.gender!;
          }

          this.imageUrl = Object.assign({}, this.user.profileImageUrl);
          console.log('Logged in');
        },
        error: (fail) => {
          this.router.navigateByUrl('/notFound');
          console.error(fail);
        },
      });
    }
  }

  getUserWalks() {
    this.walkService.getUserWalks(this.user.id).subscribe({
      next: (data) => {
        this.walks = data;
        console.log(this.walks);
      },
      error: (fail) => {
        this.router.navigateByUrl('/notFound');
        console.error(fail);
      },
    });
  }
  clickPhotos() {
    this.displayPhotos = !this.displayPhotos;
  }
  onClickWalks() {
    this.displayWalks = !this.displayWalks;
  }

  followUser() {
    let id: string = this.route.snapshot.paramMap.get('id')!;
    this.userService.followUser(+id).subscribe({
      next: () => {
        this.getUser();
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  adminDeactivateAccount(){
    let id =this.route.snapshot.paramMap.get('id')!;
    this.userService.adminDisableUser(+id).subscribe({
      next: () => {
        this.getUser();
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

}
