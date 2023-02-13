import { UserService } from 'src/app/services/user.service';
import { Component } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-ads',
  templateUrl: './ads.component.html',
  styleUrls: ['./ads.component.css']
})
export class AdsComponent {

  users:User[] = []

  constructor(private userService:UserService){
    this.reload();
  }

  reload( ){
    this.users = []
    this.userService.findUsersByPreferences().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        console.error('Error in AdsComponent.reload');
        console.error(err);
      }
    })

  }
}
