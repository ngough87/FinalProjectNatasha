import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { Component } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent {

  friends : User[] = [];

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute){

  }
  ngOnInit() {
    this.loadFriends();
  }


  loadFriends(){
    // let idString = this.route.snapshot.paramMap.get('id');
    // let id = +idString!;
    // if (!isNaN(id)) {
    let id = localStorage.getItem("currentUserId");
    if(!id && !isNaN(+id!)){
    this.userService.findFriends(+id!).subscribe ({
      next: (data) => {
        this.friends = data;

      },
      error: (error) => {
        console.error('friendComponent.loadFriends: error loading friends');

        console.error(error);

      }
      })
    } else  {
      this.router.navigateByUrl('/IdNotANumber');
  }
  }

  viewProfile(user: User){
    this.router.navigateByUrl('/profile/' + user.id);
  }



  deleteFriend(user: User){}
}
