import {Component, Input, OnInit} from '@angular/core';
import {Ingredient} from '../../../../shared/model/domain/Ingredient';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../../shared/model/util/SubmitType.enum';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Submit} from '../../../../shared/model/util/Submit';
import {Medication} from '../../../../shared/model/domain/Medication';
import {MedicationType} from '../../../../shared/model/domain/MedicationType.enum';
import {MedicationSharedService} from '../../../../core/services/shared/medication.shared.service';

@Component({
  selector: 'app-medication-form',
  templateUrl: './medication-form.component.html',
  styleUrls: ['./medication-form.component.css']
})
export class MedicationFormComponent implements OnInit
{

  @Input("additional-data")
  ingredients: Ingredient[];
  form: FormGroup;
  selected: Medication;
  selectedIngredients: Ingredient[];
  selectSubscription: Subscription;

  types: string[];


  constructor(private fb: FormBuilder,
              private sharedService: MedicationSharedService)
  {
    this.selected = null;
    this.selectedIngredients = [];
    this.types = Object.keys(MedicationType);

    this.createForm(null);
    this.selectSubscription = this.sharedService.getSelectClick()
      .subscribe((res: Medication) =>
      {
        this.selected = res;

        for (let a of this.selected.ingredients)
          this.selectedIngredients.push(a);

        this.createForm(res);
      })
  }


  ngOnInit()
  {
    console.log(this.ingredients);
  }


  createForm(selected: Medication)
  {
    this.form = this.fb.group(
      {
        name: selected ? selected.name : '',
        type: selected ? selected.type : '',
      });
  }


  submit()
  {
    let submit = new Submit();

    submit.value = new Medication();
    submit.type = this.selected ? SubmitType.UPDATE : SubmitType.CREATE;
    submit.value.id = this.selected ? this.selected.id : null;
    submit.value.name = this.form.get('name').value;
    submit.value.type = this.form.get('type').value;
    submit.value.ingredients = this.selectedIngredients;

    this.form.reset();
    this.selected = null;
    this.selectedIngredients = [];

    this.sharedService.sendClearClick(null);
    this.sharedService.sendSaveClick(submit);
  }


  clearClicked()
  {
    this.sharedService.sendClearClick(null);
    this.selected = null;
    this.selectedIngredients = [];
    this.form.reset();
  }


  addItem(id: any)
  {
    let item = null;
    let found = false;

    for (let i of this.ingredients)
    {
      if (i.id === id)
      {
        item = i;
        break;
      }
    }

    for (let i of this.selectedIngredients)
    {
      if (i.id === item.id)
      {
        found = true;
        break;
      }
    }

    if (!found)
      this.selectedIngredients.push(item);

  }


  removeItem(id: any)
  {
    for (let i of this.selectedIngredients)
    {
      if (i.id === id)
      {
        let idx = this.selectedIngredients.indexOf(i);
        this.selectedIngredients.splice(idx, 1);
        break;
      }
    }
  }

}
