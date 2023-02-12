import { MessageService } from './../../services/message.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Message } from 'src/app/models/message';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css'],
})
export class MessagesComponent implements OnInit {
  messages: Message[] = [];
  sender: User = new User();
  receiver: User = new User();

  constructor(
    private auth: AuthService,
    private router: Router,
    private messageService: MessageService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  reload(): void {
    this.messageService.index().subscribe({
      next: (data) => {
        this.messages = data;
      },
      error: (err) => {
        console.error('Message.reload(): error loading message');
        console.error(err);
      },
    });
  }

  createNewMessage(message: Message, receiverId: number) {
    message.sender = new User();

    message.sender.id = Number(localStorage.getItem('currentUserId'));

    this.messageService.create(message).subscribe({
      next: (data) => {
        console.log(data);
      },
    });
  }

  delete(id: number) {
    this.messageService.destroy(id).subscribe({
      next: () => {
        this.router.navigateByUrl('/messages');
      },
      error: (error) => {
        console.error('MessageComponent.delete message: error deleting');
        console.error(error);
      },
    });
  }
}
