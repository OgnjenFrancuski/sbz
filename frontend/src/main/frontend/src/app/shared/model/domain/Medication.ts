import {Ingredient} from './Ingredient';
import {MedicationType} from './MedicationType.enum';
import {Type} from 'class-transformer';

export class Medication
{
  id: number;
  name: string;
  type: MedicationType;

  @Type(() => Ingredient)
  ingredients: Ingredient[];

  constructor();
  constructor(id: number, name: string, type: MedicationType, ingredients: Ingredient[]);

  constructor(id?: number, name?: string, type?: MedicationType, ingredients?: Ingredient[])
  {
    this.id = id;
    this.name = name;
    this.type = type;
    this.ingredients = ingredients;
  }
}
