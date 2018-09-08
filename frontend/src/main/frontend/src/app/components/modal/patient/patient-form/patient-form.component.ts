import {Component, Input, OnInit} from '@angular/core';
import {Patient} from '../../../../shared/model/domain/Patient';
import {Ingredient} from '../../../../shared/model/domain/Ingredient';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Submit} from '../../../../shared/model/util/Submit';
import {SubmitType} from '../../../../shared/model/util/SubmitType.enum';
import {PatientSharedService} from '../../../../core/services/shared/patient.shared.service';


@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit
{
  @Input("additional-data")
  ingredients: Ingredient[];
  form: FormGroup;
  selected: Patient;
  allergics: Ingredient[];
  selectSubscription: Subscription;


  constructor(private fb: FormBuilder,
              private sharedService: PatientSharedService)
  {
    this.selected = null;
    this.allergics = [];

    this.createForm(null);
    this.selectSubscription = this.sharedService.getSelectClick()
      .subscribe((res: Patient) =>
      {
        this.selected = res;

        for (let a of this.selected.allergicIngredients)
          this.allergics.push(a);

        this.createForm(res);
      })
  }


  ngOnInit()
  {
    console.log(this.ingredients);
  }


  createForm(selected: Patient)
  {
    this.form = this.fb.group(
      {
        firstName: selected ? selected.firstName : '',
        lastName: selected ? selected.lastName : '',
        personalId: selected ? selected.personalId : '',
        healthCardId: selected ? selected.healthCardId : '',
      });
  }


  submit()
  {
    let submit = new Submit();

    submit.value = new Patient();
    submit.type = this.selected ? SubmitType.UPDATE : SubmitType.CREATE;
    submit.value.id = this.selected ? this.selected.id : null;
    submit.value.firstName = this.form.get('firstName').value;
    submit.value.lastName = this.form.get('lastName').value;
    submit.value.personalId = this.form.get('personalId').value;
    submit.value.healthCardId = this.form.get('healthCardId').value;
    submit.value.allergicIngredients = this.allergics;

    this.form.reset();
    this.selected = null;
    this.allergics = [];

    this.sharedService.sendClearClick(null);
    this.sharedService.sendSaveClick(submit);
  }


  clearClicked()
  {
    this.sharedService.sendClearClick(null);
    this.selected = null;
    this.allergics = [];
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

    for (let i of this.allergics)
    {
      if (i.id === item.id)
      {
        found = true;
        break;
      }
    }

    if (!found)
      this.allergics.push(item);

  }


  removeItem(id: any)
  {
    for (let i of this.allergics)
    {
      if (i.id === id)
      {
        let idx = this.allergics.indexOf(i);
        this.allergics.splice(idx, 1);
        break;
      }
    }
  }


}
