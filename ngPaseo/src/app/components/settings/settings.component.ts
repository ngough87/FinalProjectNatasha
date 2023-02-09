import { GenderService } from './../../services/gender.service';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { User } from './../../models/user';
import { Component } from '@angular/core';
import { Gender } from 'src/app/models/gender';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {

  user: User = new User();

  genders: Gender[] = [];

  imageUrl: string = '';

constructor(private auth: AuthService, private genService: GenderService, private router: Router){}

ngOnInit() {
this.getLoggedInUser();
// this.getGenders();

}

getLoggedInUser(){
  this.auth.getLoggedInUser().subscribe({
    next: (data) => {
      console.log(data);
      this.user = data;
      this.imageUrl = Object.assign({}, this.user.profileImageUrl);
      console.log("Logged in");
    },
    error: (fail) => {
      this.router.navigateByUrl('/notFound')
      console.error(fail);
    }

  })
}
updateUser(user: User){}

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
