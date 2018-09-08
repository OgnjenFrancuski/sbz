import {Component, Input, OnInit} from '@angular/core';
import {SharedService} from '../../../../core/services/shared/shared.service';
import {Disease} from '../../../../shared/model/domain/Disease';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../../shared/model/util/SubmitType.enum';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Submit} from '../../../../shared/model/util/Submit';
import {Symptom} from '../../../../shared/model/domain/Symptom';
import {DiseaseGroup} from '../../../../shared/model/domain/DiseaseGroup.enum';
import {DiseaseSharedService} from '../../../../core/services/shared/disease.shared.service';

@Component({
  selector: 'app-disease-form',
  templateUrl: './disease-form.component.html',
  styleUrls: ['./disease-form.component.css']
})
export class DiseaseFormComponent implements OnInit
{

  @Input("additional-data")
  symptoms: Symptom[];

  form: FormGroup;

  selected: Disease;

  selectedSpecificSymptoms: Symptom[];
  selectedGenericSymptoms: Symptom[];

  selectSubscription: Subscription;

  types: string[];


  constructor(private fb: FormBuilder,
              private sharedService: DiseaseSharedService)
  {
    this.selected = null;
    this.selectedSpecificSymptoms = [];
    this.selectedGenericSymptoms = [];

    this.types = Object.keys(DiseaseGroup);

    this.createForm(null);

    this.selectSubscription = this.sharedService.getSelectClick()
      .subscribe((res: Disease) =>
      {
        this.selected = res;

        for (let a of this.selected.genericSymptoms)
          this.selectedGenericSymptoms.push(a);

        for (let a of this.selected.specificSymptoms)
          this.selectedSpecificSymptoms.push(a);

        this.createForm(res);
      })
  }


  ngOnInit() {}


  createForm(selected: Disease)
  {
    this.form = this.fb.group(
      {
        name: selected ? selected.name : '',
        group: selected ? selected.group : '',
      });
  }


  submit()
  {
    let submit = new Submit();

    submit.value = new Disease();
    submit.type = this.selected ? SubmitType.UPDATE : SubmitType.CREATE;
    submit.value.id = this.selected ? this.selected.id : null;
    submit.value.name = this.form.get('name').value;
    submit.value.group = this.form.get('group').value;

    submit.value.specificSymptoms = this.selectedSpecificSymptoms;
    submit.value.genericSymptoms = this.selectedGenericSymptoms;

    this.form.reset();
    this.selected = null;
    this.selectedSpecificSymptoms = [];
    this.selectedGenericSymptoms = [];

    this.sharedService.sendClearClick(null);
    this.sharedService.sendSaveClick(submit);
  }


  clearClicked()
  {
    this.sharedService.sendClearClick(null);
    this.selected = null;
    this.selectedSpecificSymptoms = [];
    this.selectedGenericSymptoms = [];
    this.form.reset();
  }


  addItem(id: any, type: string)
  {
    let item = null;
    let selectedList = type == 'SPECIFIC' ? this.selectedSpecificSymptoms : this.selectedGenericSymptoms;
    let found = false;

    for (let i of this.symptoms)
    {
      if (i.id === id)
      {
        item = i;
        break;
      }
    }

    for (let i of selectedList)
    {
      if (i.id === item.id)
      {
        found = true;
        break;
      }
    }

    if (!found)
      selectedList.push(item);
  }


  removeItem(id: any, type: string)
  {
    let selectedList = type == 'SPECIFIC' ? this.selectedSpecificSymptoms : this.selectedGenericSymptoms;

    for (let i of selectedList)
    {
      if (i.id === id)
      {
        let idx = selectedList.indexOf(i);
        selectedList.splice(idx, 1);
        break;
      }
    }
  }

}
