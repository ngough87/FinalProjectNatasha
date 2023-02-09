
import { AddressService } from './../../services/address.service';
import { UserService } from './../../services/user.service';
import { GenderService } from './../../services/gender.service';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { User } from './../../models/user';
import { Component } from '@angular/core';
import { Gender } from 'src/app/models/gender';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {

  user: User = new User();

  genders: Gender[] = [];

  imageUrl: string = '';



constructor(private auth: AuthService, private genService: GenderService, private router: Router, private userService: UserService, private addressService: AddressService){}

ngOnInit() {
this.getLoggedInUser();
// this.getGenders();

}

getLoggedInUser(){
  this.auth.getLoggedInUser().subscribe({
    next: (data) => {
      console.log(data);
      this.user = data;
      if(!this.user.address)      {
      this.user.address = new Address();
    }
      if(!this.user.gender){
      this.user.gender = new Gender();
    }
      this.imageUrl = Object.assign({}, this.user.profileImageUrl);
      console.log("Logged in");
    },
    error: (fail) => {
      this.router.navigateByUrl('/notFound')
      console.error(fail);
    }

  })
}
updateUser(user: User){
  this.userService.updateUser(user, user.username, user.password).subscribe({
    next: (data) => {
      this.router.navigateByUrl('/profile/' + data.id);
    },
    error: (fail) => {
      this.router.navigateByUrl('/notFound')
      console.error(fail);
    }
  })
}

updateAddress(user: User){
  this.addressService.updateAddress(user.address, user.username, user.password).subscribe({
    next: (data) => {
      console.log(data);
      user.address = data;
      this.updateUser(user);
    },
    error: () => {
      this.addressService.createAddress(user.address, user.username, user.password).subscribe({
        next: (data) => {
          console.log(data);
          user.address = data;
          this.updateUser(user);

        },
        error: (fail) => {
          this.router.navigateByUrl('/notFound')
          console.error(fail);
        }
      })
    }
  })
}

getGenders(){
  this.genService.index(this.user.username, this.user.password).subscribe({
    next: (data) => {
      this.genders = data;
    },
    error: (fail) => {
      this.router.navigateByUrl('/notFound')
      console.error(fail);
    }
  })
}
returnHome(){
  this.router.navigateByUrl('/home')
}
}
