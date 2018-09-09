import { Component, OnInit } from '@angular/core';
import {Patient} from '../../../shared/model/domain/Patient';
import {PatientService} from '../../../core/services/http/patient.service';
import {KieService} from '../../../core/services/http/kie.service';
import {PatientSharedService} from '../../../core/services/shared/patient.shared.service';
import {IngredientSharedService} from '../../../core/services/shared/ingredient.shared.service';

@Component({
  selector: 'app-report-page',
  templateUrl: './report-page.component.html',
  styleUrls: ['./report-page.component.css'],
  providers: [PatientSharedService, IngredientSharedService]
})
export class ReportPageComponent implements OnInit {

  data: Patient[];
  what: string;

  constructor(private service: KieService) { }

  ngOnInit() {}


  getChronical()
  {
    this.service.getChronical()
      .subscribe((res: Patient[]) =>
      {
        this.data = res;
        this.what = "patients with possible chronic diseases";
      })

  }

  getAddicted()
  {
    this.service.getAddicted()
      .subscribe((res: Patient[]) =>
      {
        this.data = res;
        this.what = "patients who are possible addicts";
      })
  }

  getWeak()
  {
    this.service.getWeak()
      .subscribe((res: Patient[]) =>
      {
        this.what = "patients with weak immune system";
        this.data = res;
      })
  }
}
