import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Patient} from '../../../../shared/model/domain/Patient';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Physician} from '../../../../shared/model/domain/Physician';

@Component({
  selector: 'app-physician-table',
  templateUrl: './physician-table.component.html',
  styleUrls: ['./physician-table.component.css']
})
export class PhysicianTableComponent implements OnInit
{

  @Input("data")
  data: Physician[];

  @Input("show-delete")
  showDelete: boolean;

  @Input("show-update")
  showUpdate: boolean;


  constructor(private sharedService: SharedService) { }

  ngOnInit() {}

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
