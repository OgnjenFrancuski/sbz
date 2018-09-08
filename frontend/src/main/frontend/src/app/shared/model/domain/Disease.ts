import {Symptom} from './Symptom';
import {Type} from 'class-transformer';
import {DiseaseGroup} from './DiseaseGroup.enum';

export class Disease
{
  id: number;
  name: string;
  group: DiseaseGroup;

  @Type(() => Symptom)
  genericSymptoms: Symptom[];

  @Type(() => Symptom)
  specificSymptoms: Symptom[];

  constructor();
  constructor(id?: number, name?: string, group?: DiseaseGroup, genericSymptoms?: Symptom[], specificSymptoms?: Symptom[]);

  constructor(id?: number, name?: string, group?: DiseaseGroup, genericSymptoms?: Symptom[], specificSymptoms?: Symptom[]) {
    this.id = id;
    this.name = name;
    this.group = group;
    this.genericSymptoms = genericSymptoms;
    this.specificSymptoms = specificSymptoms;
  }
}
