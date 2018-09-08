import {Component, Input, OnInit} from '@angular/core';
import {Symptom} from '../../../../shared/model/domain/Symptom';
import {Ingredient} from '../../../../shared/model/domain/Ingredient';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Patient} from '../../../../shared/model/domain/Patient';
import {SymptomSharedService} from '../../../../core/services/shared/symptom.shared.service';

@Component({
  selector: 'app-symptom-table',
  templateUrl: './symptom-table.component.html',
  styleUrls: ['./symptom-table.component.css']
})
export class SymptomTableComponent implements OnInit
{
  @Input("data")
  data: Symptom[];

  @Input("show-delete")
  showDelete: boolean;

  @Input("show-update")
  showUpdate: boolean;

  @Input("show-remove")
  showRemove: boolean;

  @Input("show-add")
  showAdd: boolean;

  constructor(private sharedService: SymptomSharedService) { }


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


  addClickedEvent(symptom: Symptom)
  {
    this.sharedService.sendAddClick(symptom);
  }


  removeClickedEvent(symptom: Symptom)
  {
    this.sharedService.sendRemoveClick(symptom);
  }

}
