import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Patient} from '../../../../shared/model/domain/Patient';
import {PatientSharedService} from '../../../../core/services/shared/patient.shared.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  @Input("data")
  patient: Patient;

  modalRef: BsModalRef;

  constructor(private modalService: BsModalService) { }


  ngOnInit() {
  }

  openAllergiesModal(template: TemplateRef<any>, data: any)
  {
    this.patient = data;
    this.modalRef = this.modalService.show(template);
  }

}
