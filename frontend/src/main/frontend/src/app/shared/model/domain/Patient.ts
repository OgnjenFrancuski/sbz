import {Ingredient} from './Ingredient';
import {Type} from 'class-transformer';

export class Patient
{
  id: number;
  firstName: string;
  lastName: string;
  personalId: string;
  healthCardId: string;

  @Type(() => Ingredient)
  allergicIngredients: Ingredient[];


  constructor();
  constructor(id: number, firstName: string, lastName: string, personalId: string, healthCardId: string, allergicIngredients: Ingredient[]);

  constructor(id?: number, firstName?: string, lastName?: string, personalId?: string, healthCardId?: string, allergicIngredients?: Ingredient[])
  {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.personalId = personalId;
    this.healthCardId = healthCardId;
    this.allergicIngredients = allergicIngredients;
  }
}
