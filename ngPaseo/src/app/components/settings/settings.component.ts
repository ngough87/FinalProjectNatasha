
import { AddressService } from './../../services/address.service';
import { UserService } from './../../services/user.service';
import { GenderService } from './../../services/gender.service';
import { Router, ActivatedRoute } from '@angular/router';
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
  address: Address = new Address;
  userGender = new Gender();
  genders: Gender[] = [];

  imageUrl: string = '';



constructor(private auth: AuthService, private genService: GenderService, private router: Router,
  private userService: UserService, private addressService: AddressService, private route: ActivatedRoute){}

ngOnInit() {
this.getLoggedInUser();
this.getGenders();

}

getLoggedInUser(){
  this.auth.getLoggedInUser().subscribe({
    next: (data) => {
      let idString = this.route.snapshot.paramMap.get('id');
      let id = +idString!;
      if (!isNaN(id)) {

      this.userService.findUser(id);
      }
      console.log(data);
      this.user = data;
      console.log(this.user);
      if(!this.user.address)      {
        this.user.address = new Address();
      } else {
        this.address = data.address!;
      }
      if(!this.user.gender){
      this.user.gender = new Gender();
      } else {
        this.userGender = data.gender!;
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
  this.userService.updateUser(user).subscribe({
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
  console.log(user);
  // console.log(this.user);
  if(user.address!.id ===0) {
    this.addressService.createAddress(user.address!).subscribe({
      next: (data) => {
        // console.log(data);
        user.address = data;
        this.updateUser(user);

      },
      error: (fail) => {
        this.router.navigateByUrl('/notFound')
        console.error(fail);
      }
    })
  }
  else {
  console.log('************' + user.address!.street);
  this.addressService.updateAddress(user.address!).subscribe({
    next: (data) => {
      user.address = this.address;
      this.updateUser(user);
    },
    error: (err) => {
      console.error('Error updateAddress(): ' + err);
    }
  })
}
}

getGenders(){
  this.genService.index().subscribe({
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
delete(id: number){
  this.userService.delete(id).subscribe({
    next:() =>{
    this.auth.logout();
    this.router.navigateByUrl('/home');

    },
    error: (error) =>{
      console.error('WalkPageComponent.delete profile: error deleting');
      console.error(error);
    }
  });
}

}
