import { Address } from "./address";

export class WalkLocation {

id: number;
name: string;
description: string;
imageUrl: string;
address: Address;



constructor(
  id: number = 0,
  name: string = '',
  description: string = '',
  imageUrl: string = '',
  address: Address = new Address()


){

  this.id = id;
  this.name = name;
  this.description = description;
  this.imageUrl =  imageUrl;
  this.address = address;

}

}
