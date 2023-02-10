import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

  constructor(private auth: AuthService, private router:Router){}

  ngOnInit(){

this.checkLogin();

  }

  checkLogin(){
    return this.auth.checkLogin();
  }

  registerRedir() {
    this.router.navigateByUrl('/register');
  }

}
