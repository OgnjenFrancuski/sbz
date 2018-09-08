import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Patient} from '../../../../shared/model/domain/Patient';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {PatientSharedService} from '../../../../core/services/shared/patient.shared.service';
import {Diagnosis} from '../../../../shared/model/domain/Diagnosis';
import {Router} from '@angular/router';

@Component({
  selector: 'app-diagnosis-table',
  templateUrl: './diagnosis-table.component.html',
  styleUrls: ['./diagnosis-table.component.css']
})
export class DiagnosisTableComponent implements OnInit {

  @Input("data")
  data: Diagnosis[];


  constructor(private router: Router) { }

  ngOnInit() {}


  redirectToDiagnosisDetails(id: number)
  {
    this.router.navigate(['/diagnoses', id]);
  }

}
