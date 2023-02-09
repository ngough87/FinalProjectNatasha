import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { User } from './../../models/user';
import { Component } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {

  user: User = new User();

constructor(private auth: AuthService, private router: Router){}

getLoggedInUser(){
  this.auth.getLoggedInUser().subscribe({
    next: (data) => {
      this.user = data;
      console.log("Logged in");
    },
    error: (fail) => {
      this.router.navigateByUrl('/notFound')
      console.error(fail);
    }

  })
}
updateUser(user: User){}
}
