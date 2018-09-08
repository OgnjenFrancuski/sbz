import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {classToPlain, plainToClass} from 'class-transformer';
import {Medication} from '../../../shared/model/domain/Medication';
import {Diagnosis} from '../../../shared/model/domain/Diagnosis';
import {Disease} from '../../../shared/model/domain/Disease';
import {DiseaseRank} from '../../../shared/model/domain/DiseaseRank';
import {Patient} from '../../../shared/model/domain/Patient';


@Injectable()
export class KieService
{

  private readonly urlBase: string = '/api';
  private readonly urlResource: string = '/kie';

  constructor(private http: HttpClient) {}


  runDiagnostics(obj: Diagnosis): Observable<Disease[]>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource + '/diagnostics/run';

    return this.http.post(url, json)
      .map((res: any) => plainToClass(Diagnosis, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  validateDiagnostics(obj: Diagnosis): Observable<Medication[]>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource + '/diagnostics/validation';

    return this.http.post(url, json)
      .map((res: any) => plainToClass(Medication, res))
      .catch((error: any) => this.throwError(error));
  }


  getSymptomsSortedByImportance(obj: string): Observable<Disease>
  {
    let url = this.urlBase + this.urlResource + '/diagnostics/symptom-order-of-disease';

    return this.http.post(url, obj)
      .map((res: any) => plainToClass(Disease, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  getDiseaseRankingBySymptoms(obj: Diagnosis): Observable<DiseaseRank[]>
  {
    let url = this.urlBase + this.urlResource + '/diagnostics/disease-ranking';

    return this.http.post(url, obj)
      .map((res: any) => plainToClass(DiseaseRank, res))
      .catch((error: any) => this.throwError(error));
  }


  getChronical(): Observable<Patient[]>
  {
    let url = this.urlBase + this.urlResource + '/diagnostics/chronical';

    return this.http.get(url)
      .map((res: any) => plainToClass(Patient, res))
      .catch((error: any) => this.throwError(error));
  }


  getAddicted(): Observable<Patient[]>
  {
    let url = this.urlBase + this.urlResource + '/diagnostics/addicts';

    return this.http.get(url)
      .map((res: any) => plainToClass(Patient, res))
      .catch((error: any) => this.throwError(error));
  }


  getWeak(): Observable<Patient[]>
  {
    let url = this.urlBase + this.urlResource + '/diagnostics/weak-immune-system';

    return this.http.get(url)
      .map((res: any) => plainToClass(Patient, res))
      .catch((error: any) => this.throwError(error));
  }


  private throwError(error: any)
  {
    if (error.status == 404)
      return Observable.throw('Not found');
    else
      return Observable.throw(error.json().error || 'Server error');
  }

}
