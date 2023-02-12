import { Component } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-ads',
  templateUrl: './ads.component.html',
  styleUrls: ['./ads.component.css']
})
export class AdsComponent {

  users:User[] = []

  constructor(){}

  reload( ){
    this.users = []}
}
