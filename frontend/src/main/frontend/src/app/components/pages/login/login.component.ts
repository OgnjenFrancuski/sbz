import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ToasterConfig, ToasterService} from 'angular5-toaster/dist';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../core/services/security/auth.service';
import {SpaceValidator} from '../../../shared/validators/space.validator';
import {AppError} from '../../../shared/errors/app-error';
import {BadRequestError} from '../../../shared/errors/bad-request-error';
import {Login} from '../../../shared/model/util/Login';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit
{
  form: FormGroup;
  returnURL = '';
  toasterConfig: ToasterConfig;

  constructor(private fb: FormBuilder, private authService: AuthService,
              private router: Router, private route: ActivatedRoute, private toasterService: ToasterService)
  {
    this.form = this.fb.group({
      username: ['', [Validators.required, SpaceValidator.cannotContainSpace]],
      password: ['', Validators.required]
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }

  ngOnInit()
  {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get username()
  {
    return this.form.get('username');
  }

  get password()
  {
    return this.form.get('password');
  }

  login()
  {
    let login = new Login(this.username.value, this.password.value);

    this.authService.login(login)
      .subscribe((loggedIn: boolean) => {
        if (loggedIn)
          this.router.navigate([this.returnURL]);
        else
          this.toasterService.pop('error', 'Error', 'Invalid login!');
      }, (error: AppError) => {
        if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error','Invalid login!');
        else {
          this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
          throw error;
        }
      });
  }

}
