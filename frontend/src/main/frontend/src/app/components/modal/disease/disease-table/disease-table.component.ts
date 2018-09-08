import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Medication} from '../../../../shared/model/domain/Medication';
import {Disease} from '../../../../shared/model/domain/Disease';
import {DiseaseSharedService} from '../../../../core/services/shared/disease.shared.service';

@Component({
  selector: 'app-disease-table',
  templateUrl: './disease-table.component.html',
  styleUrls: ['./disease-table.component.css']
})
export class DiseaseTableComponent implements OnInit
{

  @Input("data")
  data: Disease[];

  @Input("show-delete")
  showDelete: boolean;

  @Input("show-update")
  showUpdate: boolean;

  @Input("show-add")
  showAdd: boolean;

  @Input("show-remove")
  showRemove: boolean;

  selected: Disease;
  modalRef: BsModalRef;


  constructor(private modalService: BsModalService,
              private sharedService: DiseaseSharedService) { }

  ngOnInit() {}


  openSpecificSymptomsModal(template: TemplateRef<any>, data: any)
  {
    this.selected = data;
    this.modalRef = this.modalService.show(template);
  }

  openGenericSymptomsModal(template: TemplateRef<any>, data: any)
  {
    this.selected = data;
    this.modalRef = this.modalService.show(template);
  }


  updateClickedEvent(d: Medication)
  {
    this.sharedService.sendUpdateClick(d);
    this.sharedService.sendSelectClick(d);
  }

  deleteClickedEvent(id: number)
  {
    this.sharedService.sendDeleteClick(id);
  }

  addClickedEvent(d: Disease)
  {
    this.sharedService.sendAddClick(d);
  }

  removeClickedEvent(d: Disease)
  {
    this.sharedService.sendRemoveClick(d);
  }

}
