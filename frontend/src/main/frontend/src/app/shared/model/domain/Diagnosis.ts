import {Physician} from './Physician';
import {Patient} from './Patient';
import {Symptom} from './Symptom';
import {Disease} from './Disease';
import {Medication} from './Medication';
import {Type} from 'class-transformer';

export class Diagnosis
{
  id: number;
  bodyTemperature: number;
  date: Date;

  @Type(() => Physician)
  physician: Physician;


  @Type(() => Patient)
  patient: Patient;


  @Type(() => Symptom)
  symptoms: Symptom[];


  @Type(() => Disease)
  diseases: Disease[];


  @Type(() => Medication)
  prescribedMedications: Medication[];


  constructor();

  constructor(id: number,
              bodyTemperature: number,
              date: Date,
              physician: Physician,
              patient: Patient,
              symptoms: Symptom[],
              diseases: Disease[],
              prescribedMedications: Medication[]);

  constructor(id?: number,
              bodyTemperature?: number,
              date?: Date,
              physician?: Physician,
              patient?: Patient,
              symptoms?: Symptom[],
              diseases?: Disease[],
              prescribedMedications?: Medication[])
  {
    this.id = id;
    this.bodyTemperature = bodyTemperature;
    this.date = date;
    this.physician = physician;
    this.patient = patient;
    this.symptoms = symptoms;
    this.diseases = diseases;
    this.prescribedMedications = prescribedMedications;
  }
}
