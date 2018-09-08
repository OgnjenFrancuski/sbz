import { Injectable } from '@angular/core';
import {Diagnosis} from '../../../shared/model/domain/Diagnosis';
import {Observable} from 'rxjs/Observable';
import {classToPlain, plainToClass} from 'class-transformer';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class DiagnosisService {

  private readonly urlBase: string = '/api';
  private readonly urlResource: string = '/diagnosis';


  constructor(private http: HttpClient) {}


  create(obj: Diagnosis): Observable<Diagnosis>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.post(url, json)
      .map((res: any) => plainToClass(Diagnosis, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  update(obj: Diagnosis): Observable<Diagnosis>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.put(url, json)
      .map((res: any) => plainToClass(Diagnosis, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  getOne(id: number): Observable<Diagnosis>
  {
    let url = this.urlBase + this.urlResource + '/' + id;

    return this.http.get(url)
      .map((res: any) => plainToClass(Diagnosis, res))
      .catch((error: any) => this.throwError(error));
  }


  getAll(): Observable<Diagnosis[]>
  {
    let url = this.urlBase + this.urlResource;

    return this.http.get(url)
      .map((res: any) => plainToClass(Diagnosis, res))
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
