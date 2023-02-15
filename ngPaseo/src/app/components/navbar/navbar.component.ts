import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  user:User = new User();
  loggedInUser:User | null = null;

  constructor(private auth:AuthService, private router:Router){}

  ngOnInit() {
    if (this.loggedIn()) {
      this.auth.getLoggedInUser().subscribe({
        next: (data) => {
          this.loggedInUser = data;
        },
        error: (err) => {
          console.error('Error logging into user:');
          console.error(err);
        }
      })
    }
  }

  loggedIn():boolean {
    return this.auth.checkLogin();
  }

  login(user:User) {
    this.auth.login(user.username, user.password).subscribe({
      next: (data) => {
        this.loggedInUser = data;
        console.log('Logged in as: ' + data.username);
        this.router.navigateByUrl('/home');
        location.reload();
      },
      error: (err) => {
        console.error('Error logging into user:');
        console.error(err);
      }
    });
  }

  logout() {
    this.auth.logout();
  }

}
