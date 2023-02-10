export class Walk {

id: number;
name: string;
date: string;
description: string;
walkCategory: string;
walkType: string;
walkLocation: string;
userId: number;
enabled: boolean;
startTime: string;
endTime: string;
imageUrl: string;


constructor(

id: number = 0,
name: string = '',
date: string = '',
description: string = '',
walkCategory: string = '',
walkType: string = '',
walkLocation: string = '',
userId: number = 0,
enabled: boolean = false,
startTime: string = '',
endTime: string = '',
imageUrl: string = ''

){
  this.id = id;
  this.name = name;
  this.date = date;
  this.description = description;
  this.walkCategory = walkCategory;
  this.walkType = walkType;
  this.walkLocation = walkLocation;
  this.userId = id;
  this.enabled= enabled;
  this.startTime = startTime;
  this.endTime = endTime;
  this.imageUrl = imageUrl;

}

}
