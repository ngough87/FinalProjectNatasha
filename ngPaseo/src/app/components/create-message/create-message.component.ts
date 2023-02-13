import { UserService } from 'src/app/services/user.service';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from 'src/app/models/message';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-create-message',
  templateUrl: './create-message.component.html',
  styleUrls: ['./create-message.component.css'],
})
export class CreateMessageComponent {
  @Input() receiverId: number = 0;
  @Input() message: Message = new Message();
  @Input() receiverUsername: string = '';

  constructor(
    private auth: AuthService,
    private router: Router,
    private messageService: MessageService,
    private userService: UserService
  ) {}

  createNewMessage() {
    if (this.receiverUsername) {
      this.userService.findUserByUsername(this.receiverUsername).subscribe({
        next: (receiver) => {
          console.log(receiver);
          this.message.receiver = receiver;
          this.message.sender = new User();
          this.message.sender.id = Number(localStorage.getItem('currentUserId')
          );
          this.messageService.create(this.message).subscribe({
            next: (data) => {
              console.log(data);
            },
            error: (err) => {
              console.error('Failed to send message');
              console.error(err);
            },
          });
        },
      });
    } else {
      this.message.receiver = new User();
      this.message.receiver.id = this.receiverId;
      this.message.sender = new User();
      this.message.sender.id = Number(localStorage.getItem('currentUserId'));

      this.messageService.create(this.message).subscribe({
        next: (data) => {
          console.log(data);
        },
        error: (err) => {
          console.error('Failed to send message');
          console.error(err);
        },
      });
    }
  }
}
