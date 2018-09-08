import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {classToPlain, plainToClass} from 'class-transformer';
import {Physician} from '../../../shared/model/domain/Physician';
import {AuthService} from '../security/auth.service';
import {AuthorityService} from '../security/authority.service';

@Injectable()
export class PhysicianService
{

  private readonly urlBase: string = '/api';
  private readonly urlResource: string = '/physicians';

  constructor(private http: HttpClient,
              private authService: AuthorityService) {}


  create(obj: Physician): Observable<Physician>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.post(url, json)
      .map((res: any) => plainToClass(Physician, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  update(obj: Physician): Observable<Physician>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.put(url, json)
      .map((res: any) => plainToClass(Physician, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  getAll(): Observable<Physician[]>
  {
    let url = this.urlBase + this.urlResource;

    return this.http.get(url)
      .map((res: any) => plainToClass(Physician, res))
      .catch((error: any) => this.throwError(error));
  }


  findByUsername()
  {
    let username = this.authService.getCurrentUserUsername();

    let url = this.urlBase + this.urlResource + '/my/' + username;

    return this.http.get(url)
      .map((res: any) => plainToClass(Physician, [res])[0])
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
