import {Disease} from './Disease';
import {Type} from 'class-transformer';

export class DiseaseRank
{
  @Type(() => Disease)
  disease: Disease;
  count: number;


  constructor();

  constructor(disease: Disease, count: number);

  constructor(disease?: Disease, count?: number)
  {
    this.disease = disease;
    this.count = count;
  }
}
