import { Component, OnInit } from '@angular/core';
import {Symptom} from '../../../../shared/model/domain/Symptom';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../../shared/model/util/SubmitType.enum';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Submit} from '../../../../shared/model/util/Submit';
import {SymptomSharedService} from '../../../../core/services/shared/symptom.shared.service';

@Component({
  selector: 'app-symptom-form',
  templateUrl: './symptom-form.component.html',
  styleUrls: ['./symptom-form.component.css']
})
export class SymptomFormComponent implements OnInit
{

  form: FormGroup;
  selected: Symptom;
  selectSubscription: Subscription;


  constructor(private fb: FormBuilder,
              private sharedService: SymptomSharedService)
  {
    this.selected = null;

    this.createForm(null);
    this.selectSubscription = this.sharedService.getSelectClick()
      .subscribe((res: Symptom) =>
      {
        this.selected = res;
        this.createForm(res);
      })
  }

  ngOnInit() {}


  createForm(selected: Symptom)
  {
    this.form = this.fb.group(
      {
        name: selected ? selected.name : '',
      });
  }


  submit()
  {
    let submit = new Submit();

    submit.value = new Symptom();
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
