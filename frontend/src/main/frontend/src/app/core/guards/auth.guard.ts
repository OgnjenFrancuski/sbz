import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import {AuthService} from '../services/security/auth.service';
import {AuthorityService} from '../services/security/authority.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthService, public authorityService: AuthorityService, public router: Router)
  {

  }


  canActivate(): boolean
  {
    this.auth.isAuthenticated()
      .subscribe((res: boolean) =>
      {
        if (!res)
        {
          this.authorityService.removeCurrentUser();
        }
      });

    if (!this.authorityService.userLoggedIn())
    {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
