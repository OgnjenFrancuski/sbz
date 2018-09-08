import { Component, OnInit } from '@angular/core';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../../shared/model/util/SubmitType.enum';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Submit} from '../../../../shared/model/util/Submit';
import {Ingredient} from '../../../../shared/model/domain/Ingredient';
import {IngredientSharedService} from '../../../../core/services/shared/ingredient.shared.service';

@Component({
  selector: 'app-ingredient-form',
  templateUrl: './ingredient-form.component.html',
  styleUrls: ['./ingredient-form.component.css']
})
export class IngredientFormComponent implements OnInit
{

  form: FormGroup;
  selected: Ingredient;
  selectSubscription: Subscription;


  constructor(private fb: FormBuilder,
              private sharedService: IngredientSharedService)
  {
    this.selected = null;

    this.createForm(null);
    this.selectSubscription = this.sharedService.getSelectClick()
      .subscribe((res: Ingredient) =>
      {
        this.selected = res;
        this.createForm(res);
      })
  }

  ngOnInit() {}


  createForm(selected: Ingredient)
  {
    this.form = this.fb.group(
      {
        name: selected ? selected.name : '',
      });
  }


  submit()
  {
    let submit = new Submit();

    submit.value = new Ingredient();
    submit.type = this.selected ? SubmitType.UPDATE : SubmitType.CREATE;
    submit.value.id = this.selected ? this.selected.id : null;
    submit.value.name = this.form.get('name').value;

    this.form.reset();
    this.selected = null;

    this.sharedService.sendClearClick(null);
    this.sharedService.sendSaveClick(submit);
  }


  clearClicked()
  {
    this.sharedService.sendClearClick(null);
    this.selected = null;
    this.form.reset();
  }

}
