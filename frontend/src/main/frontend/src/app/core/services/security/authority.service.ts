import { Injectable } from '@angular/core';
// model
import { UsernameAuthorities } from "../../../shared/model/util/UsernameAuthorities";


@Injectable()
export class AuthorityService
{

  constructor() { }

  userLoggedIn(): boolean
  {
    return !!(this.getCurrentUserUsername());
  }


  hasAuthority(authority: string)
  {
    return (this.getCurrentUserAuthorities().indexOf(authority) != -1);
  }


  getCurrentUserUsername(): string
  {
    return localStorage.getItem('currentUserUsername');
  }


  getCurrentUserAuthorities(): string[]
  {
    return localStorage.getItem('currentUserAuthorities').split(';');
  }


  setCurrentUser(user: UsernameAuthorities): void
  {
    let a = "";
    for (let authority of user.authorities)
    {
      a += authority;
      if (user.authorities.indexOf(authority) != user.authorities.length - 1)
        a += ";";
    }

    localStorage.setItem('currentUserUsername', user.username);
    localStorage.setItem('currentUserAuthorities', a);
  }


  removeCurrentUser(): void
  {
    localStorage.removeItem('currentUserUsername');
    localStorage.removeItem('currentUserAuthorities');
  }

}
