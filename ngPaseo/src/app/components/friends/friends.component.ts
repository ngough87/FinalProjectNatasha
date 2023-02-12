import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit{

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
      console.log('Loading Friends');
    let id = localStorage.getItem("currentUserId");
    if(id && !isNaN(+id!)){
      this.userService.findFriends(+id!).subscribe ({
        next: (data) => {
          console.log('Friends loaded');
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



  deleteFriend(user: User){
    this.userService.unfollowUser(user.id).subscribe ({
      next: (data) => {
        console.log('unfollowed ' + user.username);
        this.loadFriends();
      },
      error: (error) => {
        console.error('friendComponent.deleteFriend: error deleting friend');
        console.error(error);
      }
      })
  }
}
