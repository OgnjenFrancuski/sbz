import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from "../../../core/services/security/auth.service";
import {AuthorityService} from '../../../core/services/security/authority.service';
import {Role} from '../../../shared/model/domain/Role.enum';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router,
              private authService: AuthService,
              private authorityService: AuthorityService) { }

  ngOnInit() { }

  logout()
  {
    this.authService.logout().subscribe();
    this.router.navigate(['login']);
  }


  isAdmin()
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }


  isPhysician()
  {
    return this.authorityService.hasAuthority(Role.PHYSICIAN);
  }
}
