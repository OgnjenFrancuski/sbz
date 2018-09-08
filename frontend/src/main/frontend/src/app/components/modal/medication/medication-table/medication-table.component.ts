import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Medication} from '../../../../shared/model/domain/Medication';
import {MedicationSharedService} from '../../../../core/services/shared/medication.shared.service';

@Component({
  selector: 'app-medication-table',
  templateUrl: './medication-table.component.html',
  styleUrls: ['./medication-table.component.css']
})
export class MedicationTableComponent implements OnInit
{

  @Input("data")
  data: Medication[];

  @Input("show-delete")
  showDelete: boolean;

  @Input("show-update")
  showUpdate: boolean;

  @Input("show-add")
  showAdd: boolean;

  @Input("show-remove")
  showRemove: boolean;

  selected: Medication;
  modalRef: BsModalRef;


  constructor(private modalService: BsModalService,
              private sharedService: MedicationSharedService) { }

  ngOnInit() {
  }


  openIngredientsModal(template: TemplateRef<any>, data: any)
  {
    this.selected = data;
    this.modalRef = this.modalService.show(template);
  }

  addClickedEvent(d: Medication)
  {
    this.sharedService.sendAddClick(d);
  }

  removeClickedEvent(d: Medication)
  {
    this.sharedService.sendRemoveClick(d);
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

}
