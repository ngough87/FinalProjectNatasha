import { WalkCategory } from './walk-category';
import { WalkType } from './walk-type';
import { Address } from "./address";
import { Gender } from "./gender";
import { WalkLocation } from './walk-location';

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
  preferredGender: Gender | null;
  preferredWalkType: WalkType | null;
  preferredWalkLocation: WalkLocation | null;
  preferredWalkCategory: WalkCategory | null ;
  walkType: WalkType | null;
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
    enabled: boolean = false,
    preferredGender: Gender = new Gender(),
    preferredWalkType: WalkType = new WalkType(),
  preferredWalkLocation: WalkLocation = new WalkLocation(),
  preferredWalkCategory: WalkCategory = new WalkCategory(),
  walkType: WalkType = new WalkType()

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
    this.preferredGender = preferredGender;
    this.preferredWalkType = preferredWalkType;
    this.preferredWalkLocation = preferredWalkLocation;
    this.preferredWalkCategory = preferredWalkCategory;
    this.walkType = walkType;
   }
}
