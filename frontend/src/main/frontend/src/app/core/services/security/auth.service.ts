import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
// error
import { BadRequestError } from "../../../shared/errors/bad-request-error";
import { NotFoundError } from "../../../shared/errors/not-found-error";
import { AppError } from "../../../shared/errors/app-error";
import { ForbiddenError } from "../../../shared/errors/forbidden-error";
import { ConflictError } from "../../../shared/errors/conflict-error";
// service
import { AuthorityService } from "./authority.service";
// model
import { Login } from "../../../shared/model/util/Login";
import { UsernameAuthorities } from "../../../shared/model/util/UsernameAuthorities";


@Injectable()
export class AuthService {

  private readonly urlBase: string = '/api';

  constructor(private http: HttpClient, private authorityService: AuthorityService) { }

  login(loginCredentials: Login): Observable<boolean>
  {
    return this.http.post(`${this.urlBase}/login`, loginCredentials)
      .map((currentUser: UsernameAuthorities) =>
      {
        if(currentUser.username)
        {
          this.authorityService.setCurrentUser(currentUser);
          return true;
        }
        else return false;
      }).catch(this.handleErrors);
  }


  logout(): Observable<boolean>
  {
    return this.http.get(`${this.urlBase}/logout`)
      .map(() =>
      {
        this.authorityService.removeCurrentUser();
        return true;
      }).catch(this.handleErrors);
  }


  isAuthenticated(): Observable<boolean>
  {
    return this.http.get(`${this.urlBase}/is-authenticated`)
      .map((res: any) =>
      {
        return res['authenticated'];
      }).catch(this.handleErrors);
  }


  private handleErrors(response: Response)
  {
    if(response.status === 400)
      return Observable.throw(new BadRequestError());
    else if(response.status === 403)
      return Observable.throw(new ForbiddenError());
    else if(response.status === 404)
      return Observable.throw(new NotFoundError());
    else if(response.status == 409)
      return Observable.throw(new ConflictError());
    return Observable.throw(new AppError(response));
  }
}
