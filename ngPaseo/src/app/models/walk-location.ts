export class WalkLocation {

id: number;
name: string;
description: string;
imageUrl: string;
address: string;



constructor(
  id: number = 0,
  name: string = '',
  description: string = '',
  imageUrl: string = '',
  address: string = ''


){

  this.id = id;
  this.name = name;
  this.description = description;
  this.imageUrl =  imageUrl;
  this.address = address;

}

}
