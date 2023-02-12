import { User } from "./user";

export class Message {

id: number;
contents: string;
dateSent: string;
sender: User;
receiver: User;
seen: boolean;
enabled: boolean;



constructor(

  id: number = 0,
  contents: string = '',
  dateSent: string = '',
  sender: User = new User(),
  receiver: User = new User(),
  seen: boolean = false,
  enabled: boolean = false

){
  this.id = id;
  this.contents = contents;
  this.dateSent = dateSent;
  this.sender = sender;
  this.receiver = receiver;
  this.seen = seen;
  this.enabled = enabled;


}

}
