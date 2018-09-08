import {Component, Input, OnInit} from '@angular/core';
import {Ingredient} from '../../../../shared/model/domain/Ingredient';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Physician} from '../../../../shared/model/domain/Physician';
import {Patient} from '../../../../shared/model/domain/Patient';
import {IngredientSharedService} from '../../../../core/services/shared/ingredient.shared.service';

@Component({
  selector: 'app-ingredient-table',
  templateUrl: './ingredient-table.component.html',
  styleUrls: ['./ingredient-table.component.css']
})
export class IngredientTableComponent implements OnInit
{
  @Input("data")
  data: Ingredient[];

  @Input("show-delete")
  showDelete: boolean;

  @Input("show-update")
  showUpdate: boolean;

  constructor(private sharedService: IngredientSharedService) { }


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
