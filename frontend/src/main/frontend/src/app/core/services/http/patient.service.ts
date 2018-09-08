import { Injectable } from '@angular/core';
import {Patient} from '../../../shared/model/domain/Patient';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {classToPlain, plainToClass} from 'class-transformer';

@Injectable()
export class PatientService
{
  private readonly urlBase: string = '/api';
  private readonly urlResource: string = '/patients';

  constructor(private http: HttpClient) {}


  create(obj: Patient): Observable<Patient>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.post(url, json)
      .map((res: any) => plainToClass(Patient, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  update(obj: Patient): Observable<Patient>
  {
    let json = classToPlain(obj);
    let url = this.urlBase + this.urlResource;

    return this.http.put(url, json)
      .map((res: any) => plainToClass(Patient, [res])[0])
      .catch((error: any) => this.throwError(error));
  }


  delete(id: any): Observable<any>
  {
    let url = this.urlBase + this.urlResource + '/' + id;

    return this.http.delete(url)
      .map((res: any) => true)
      .catch((error: any) => this.throwError(error));
  }

  getAll(): Observable<Patient[]>
  {
    let url = this.urlBase + this.urlResource;

    return this.http.get(url)
      .map((res: any) => plainToClass(Patient, res))
      .catch((error: any) => this.throwError(error));
  }
  //
  //
  // public getOne(id: number): Observable<Employee>
  // {
  //   let url = this.path + "/" + id;
  //   return this.http.get(url)
  //     .map((res: any) => Converter.dtoToEmployee(plainToClass(EmployeeDto, [res])[0], true, true, true))
  //     .catch((error: any) => this.throwError(error));
  // }
  //
  //
  // public update(employee: Employee): Observable<Employee>
  // {
  //   let url = this.path + "/" + employee.id;
  //   let headers = new HttpHeaders({'Content-Type': 'application/json'});
  //   let json = classToPlain(Converter.employeeToDto(employee, true, false, false));
  //   return this.http.put(url, json, {headers})
  //     .map((res: any) => Converter.dtoToEmployee(plainToClass(EmployeeDto, [res])[0], true, true, true))
  //     .catch((error: any) => this.throwError(error));
  // }
  //
  //
  // public getPage(page: number, size: number): Observable<EmployeePage>
  // {
  //   let url = this.path + "?page=" + page + "&size=" + size;
  //   return this.http.get(url)
  //     .map((res: any) => Converter.dtoToEmployeePage(plainToClass(EmployeePageDto, [res])[0]))
  //     .catch((error: any) => this.throwError(error));
  // }
  //
  //
  // public changePassword(passwordDto: PasswordDto): Observable<Boolean>
  // {
  //   let json = classToPlain(new PasswordDto(passwordDto.oldPassword, passwordDto.newPassword));
  //   let url = Paths.host + Paths.main + "password";
  //   let headers = new HttpHeaders({'Content-Type': 'application/json'});
  //   return this.http.post(url, json, {headers})
  //     .map((res: any) => res)
  //     .catch((error: any) => this.throwError(error));
  //
  // }
  //
  //
  // public serachEmployees(firstName: string, lastName: string, page: number, size: number): Observable<EmployeePage>
  // {
  //
  //   let json = {
  //     'firstName': firstName,
  //     'lastName': lastName
  //   };
  //   let url = this.path + "/search" + "?page=" + page +
  //     "&size=" + size;
  //   let headers = new HttpHeaders({'Content-Type': 'application/json'});
  //   return this.http.post(url, json, {headers})
  //     .map((res: any) => Converter.dtoToEmployeePage(plainToClass(EmployeePageDto, [res])[0]))
  //     .catch((error: any) => this.throwError(error));
  // }


  private throwError(error: any)
  {
    if (error.status == 404)
      return Observable.throw('Not found');
    else
      return Observable.throw(error.json().error || 'Server error');
  }

}
