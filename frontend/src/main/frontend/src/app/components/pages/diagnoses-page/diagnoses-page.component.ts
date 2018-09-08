import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {Patient} from '../../../shared/model/domain/Patient';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {Submit} from '../../../shared/model/util/Submit';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {IngredientService} from '../../../core/services/http/ingredient.service';
import {Subscription} from 'rxjs/Subscription';
import {PatientService} from '../../../core/services/http/patient.service';
import {Ingredient} from '../../../shared/model/domain/Ingredient';
import {Role} from '../../../shared/model/domain/Role.enum';
import {PatientSharedService} from '../../../core/services/shared/patient.shared.service';
import {Diagnosis} from '../../../shared/model/domain/Diagnosis';
import {DiagnosisService} from '../../../core/services/http/diagnosis.service';
import {SharedService} from '../../../core/services/shared/shared.service';
import {Medication} from '../../../shared/model/domain/Medication';

@Component({
  selector: 'app-diagnoses-page',
  templateUrl: './diagnoses-page.component.html',
  styleUrls: ['./diagnoses-page.component.css']
})
export class DiagnosesPageComponent implements OnInit
{

  data: Diagnosis[];

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: DiagnosisService,
              private authorityService: AuthorityService)
  {}


  ngOnInit()
  {
    this.loadDiagnoses();
  }


  loadDiagnoses()
  {
    this.service.getAll()
      .subscribe((res: Diagnosis[]) =>
      {
        this.data = res;
      });
  }
}
