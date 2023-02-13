import { MessageService } from './../../services/message.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Message } from 'src/app/models/message';
import { User } from 'src/app/models/user';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css'],
})
export class MessagesComponent implements OnInit {
  messages: Message[] = [];
  inbox:Message[] = [];
  deletedMessages: Message[] = [];
  sender: User = new User();
  receiver: User = new User();
  closeResult = '';
  receiverId:number = 0;
  viewedMessage:Message = new Message();
  createdMessage:Message = new Message();
  selected:number = 1;

  constructor(
    private auth: AuthService,
    private router: Router,
    private messageService: MessageService,
    private userService: UserService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  reload(): void {
    this.messageService.index().subscribe({
      next: (data) => {
        this.inbox = [];
        this.deletedMessages = [];
        this.messages = data;
        for (let message of this.messages) {
          console.log(message);
          if (message.enabled) {
            this.inbox.push(message);
          } else {
            this.deletedMessages.push(message);
          }
        }
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
        this.reload();
      },
      error: (error) => {
        console.error('MessageComponent.delete message: error deleting');
        console.error(error);
      },
    });
  }


  //Open reply modal
  open(content:any, message:Message) {
		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
    this.receiverId = message.receiver.id;
    this.viewedMessage = message;
	}

  //Open reply modal
  compose(content:any) {
		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-content' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
	}

	private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}
}
