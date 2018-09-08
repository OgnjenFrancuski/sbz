import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class PatientSharedService
{
    private deleteClickedSubject  = new Subject<any>();
    private saveClickedSubject    = new Subject<any>();
    private updateClickedSubject  = new Subject<any>();
    private selectClickSubject    = new Subject<any>();
    private clearClickSubject    = new Subject<any>();


    constructor() {}


    sendDeleteClick(message: any)
    {
        this.deleteClickedSubject.next(message);
    }


    getDeleteClick(): Observable<any>
    {
        return this.deleteClickedSubject.asObservable();
    }


    sendSaveClick(message: any)
    {
        this.saveClickedSubject.next(message);
    }


    getSaveClick(): Observable<any>
    {
        return this.saveClickedSubject.asObservable();
    }


    sendUpdateClick(message: any)
    {
        this.updateClickedSubject.next(message);
    }


    getUpdateClick()
    {
        return this.updateClickedSubject.asObservable();
    }


    sendSelectClick(message: any)
    {
        this.selectClickSubject.next(message);
    }


    getSelectClick()
    {
        return this.selectClickSubject.asObservable();
    }


    sendClearClick(message: any)
    {
      this.clearClickSubject.next(message);
    }


    getClearClick()
    {
      return this.clearClickSubject.asObservable();
    }
}
