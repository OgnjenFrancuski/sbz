import {Ingredient} from './Ingredient';

export class Physician
{
  id: number;
  firstName: string;
  lastName: string;
  personalId: string;

  constructor();
  constructor(id: number, firstName: string, lastName: string, personalId: string);

  constructor(id?: number, firstName?: string, lastName?: string, personalId?: string)
  {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.personalId = personalId;
  }
}
