import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Diagnosis} from '../../../../shared/model/domain/Diagnosis';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Patient} from '../../../../shared/model/domain/Patient';

@Component({
  selector: 'app-diagnosis-details',
  templateUrl: './diagnosis-details.component.html',
  styleUrls: ['./diagnosis-details.component.css']
})
export class DiagnosisDetailsComponent implements OnInit
{
  @Input("data")
  diagnosis: Diagnosis;

  modalRef: BsModalRef;

  constructor(private modalService: BsModalService) { }


  ngOnInit() {}


  openSymptomsModal(template: TemplateRef<any>, data: any)
  {
    this.modalRef = this.modalService.show(template);
  }


  openDiseasesModal(template: TemplateRef<any>, data: any)
  {
    this.modalRef = this.modalService.show(template);
  }


  openMedicationsModal(template: TemplateRef<any>, data: any)
  {
    this.modalRef = this.modalService.show(template);
  }

}
