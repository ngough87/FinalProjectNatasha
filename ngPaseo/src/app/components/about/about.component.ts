import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';



@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {

  user: User = new User();

  constructor(
    private auth: AuthService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute ){}


    ngOnInit() {

    }
    loggedIn(user: User) {
     this.auth.getLoggedInUser().subscribe({
      next: (data) => {
        this.router.navigateByUrl('/walk-page/' + data.id);
      },
      error: (fail) => {
        this.router.navigateByUrl('/home')
        console.error(fail);
      }
     })
  }
}
