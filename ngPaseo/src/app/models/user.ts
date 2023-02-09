import { Address } from "./address";
import { Gender } from "./gender";

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  description: string;
  address: Address | null;
  birthdate: Date;
  gender: Gender | null;
  role: string;
  profileImageUrl: string;
  enabled: boolean;

  constructor(

    id: number = 0,
    username: string ='',
    password: string='',
    firstName: string = '',
    lastName: string = '',
    description: string = '',
    address: Address = new Address(),
    birthdate: Date = new Date(),
    gender: Gender = new Gender(),
    role: string = "standard",
    profileImageUrl: string ='',
    enabled: boolean = false

   ) {
    this.id = id;
    this.username=username;
    this.password=password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
    this.address = address;
    this.birthdate = birthdate;
    this.gender = gender;
    this.profileImageUrl = profileImageUrl;
    this.role =role;
    this.enabled=enabled;
   }
}
