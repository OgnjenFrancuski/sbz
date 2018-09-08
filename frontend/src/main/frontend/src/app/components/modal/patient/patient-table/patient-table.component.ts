import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Patient} from '../../../../shared/model/domain/Patient';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {PatientSharedService} from '../../../../core/services/shared/patient.shared.service';

@Component({
  selector: 'app-patient-table',
  templateUrl: './patient-table.component.html',
  styleUrls: ['./patient-table.component.css']
})
export class PatientTableComponent implements OnInit {

  @Input("data")
  data: Patient[];

  @Input("show-delete")
  showDelete: boolean;

  @Input("show-update")
  showUpdate: boolean;

  @Input("show-add")
  showAdd: boolean;

  selectedPatient: Patient;
  modalRef: BsModalRef;


  constructor(private modalService: BsModalService,
              private sharedService: PatientSharedService) { }

  ngOnInit() {
  }


  openAllergiesModal(template: TemplateRef<any>, data: any)
  {
    this.selectedPatient = data;
    this.modalRef = this.modalService.show(template);
  }


  addClickedEvent(d: Patient)
  {
    this.sharedService.sendSelectClick(d);
  }


  updateClickedEvent(d: Patient)
  {
    this.sharedService.sendUpdateClick(d);
    this.sharedService.sendSelectClick(d);
  }


  deleteClickedEvent(id: number)
  {
    this.sharedService.sendDeleteClick(id);
  }

}
