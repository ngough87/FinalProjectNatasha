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
  walks: Walk[] = [];
  user: User = new User();
  followedUsers:User[] = [];
  currentUserFollowedUsers: User[] = [];
  followingUsers:User[] = [];
  address: Address = new Address();
  userGender = new Gender();
  genders: Gender[] = [];
  displayPhotos = true;
  displayWalks = false;
  displayGroups = false;
  displayInterests = false;
  followed:boolean = false;

  imageUrl: string = this.user.profileImageUrl;
  constructor(
    private auth: AuthService,
    private userService: UserService,
    private router: Router,
    private addressService: AddressService,
    private gender: GenderService,
    private walkService: WalkService,
    private route: ActivatedRoute
  ) {}

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
          this.getFollowed();
          this.getFollowers();
          this.getUserWalks();
          this.getCurrentUserFollowed();
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
          this.getFollowed();
          this.getFollowers();
          this.getUserWalks();
          this.getCurrentUserFollowed();
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

getFollowers(){
  this.userService.findFollowers(+this.user.id).subscribe({
    next: (data) => {
      this.followingUsers = data;
    },
    error: (err) => {
      console.error('Failed to get following users');
      console.error(err);
    }
  })
}

getFollowed(){
  this.userService.findFriends(+this.user.id).subscribe({
    next: (data) => {
      let id = localStorage.getItem('currentUserId');
      this.followedUsers = data;

    },
    error: (err) => {
      console.error('Failed to get followed users');
      console.error(err);
    }
  })
}

getCurrentUserFollowed() {
  let id:string = localStorage.getItem('currentUserId')!;
  this.userService.findFriends(+id).subscribe({
    next: (data) => {
      let id = localStorage.getItem('currentUserId');
      this.currentUserFollowedUsers = data;
      for (let u of this.currentUserFollowedUsers) {
        if (u.id === +this.user.id!) {
          this.followed = true;
          break;
        }
      }
    },
    error: (err) => {
      console.error('Failed to get followed users');
      console.error(err);
    }
  })
}

  clickPhotos() {
    this.displayPhotos = !this.displayPhotos;
  }
  onClickWalks() {
    this.displayWalks = !this.displayWalks;
  }

  followUser() {
    let id: string = this.route.snapshot.paramMap.get('id')!;
    if (id === null) {
      id = localStorage.getItem('currentUserId')!;
    }
    this.userService.followUser(+id).subscribe({
      next: () => {
        this.getUser();
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  unfollowUser() {
    let id: string = this.route.snapshot.paramMap.get('id')!;
    if (id === null) {
      id = localStorage.getItem('currentUserId')!;
    }
    this.userService.unfollowUser(+id).subscribe({
      next: (data) => {
        this.followed = false;
        this.getUser();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}
