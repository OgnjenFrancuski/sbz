import {Component, Input, OnInit} from '@angular/core';
import {SubmitType} from '../../../../shared/model/util/SubmitType.enum';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {Ingredient} from '../../../../shared/model/domain/Ingredient';
import {Submit} from '../../../../shared/model/util/Submit';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Physician} from '../../../../shared/model/domain/Physician';

@Component({
  selector: 'app-physician-form',
  templateUrl: './physician-form.component.html',
  styleUrls: ['./physician-form.component.css']
})
export class PhysicianFormComponent implements OnInit
{
  form: FormGroup;
  selected: Physician;
  selectSubscription: Subscription;


  constructor(private fb: FormBuilder,
              private sharedService: SharedService)
  {
    this.selected = null;

    this.createForm(null);
    this.selectSubscription = this.sharedService.getSelectClick()
      .subscribe((res: Physician) =>
      {
        this.selected = res;
        this.createForm(res);
      })
  }

  ngOnInit() {}


  createForm(selected: Physician)
  {
    this.form = this.fb.group(
      {
        firstName: selected ? selected.firstName : '',
        lastName: selected ? selected.lastName : '',
        personalId: selected ? selected.personalId : '',
      });
  }


  submit()
  {
    let submit = new Submit();

    submit.value = new Physician();
    submit.type = this.selected ? SubmitType.UPDATE : SubmitType.CREATE;
    submit.value.id = this.selected ? this.selected.id : null;
    submit.value.firstName = this.form.get('firstName').value;
    submit.value.lastName = this.form.get('lastName').value;
    submit.value.personalId = this.form.get('personalId').value;

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
