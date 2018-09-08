import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {classToPlain, plainToClass} from 'class-transformer';
import {Symptom} from '../../../shared/model/domain/Symptom';
import {HttpClient} from '@angular/common/http';
import {Ingredient} from '../../../shared/model/domain/Ingredient';
import {Physician} from '../../../shared/model/domain/Physician';

@Injectable()
export class SymptomService
{
  private readonly urlBase: string = '/api';
  private readonly urlResource: string = '/symptoms';

  constructor(private http: HttpClient) {}


  create(obj: Symptom): Observable<Symptom>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.post(url, json)
      .map((res: any) => plainToClass(Symptom, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  update(obj: Physician): Observable<Symptom>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.put(url, json)
      .map((res: any) => plainToClass(Symptom, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  delete(id: any): Observable<any>
  {
    let url = this.urlBase + this.urlResource + '/' + id;

    return this.http.delete(url)
      .map((res: any) => true)
      .catch((error: any) => this.throwError(error));
  }


  getAll(): Observable<Symptom[]>
  {
    let url = this.urlBase + this.urlResource;

    return this.http.get(url)
      .map((res: any) => plainToClass(Symptom, res))
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
