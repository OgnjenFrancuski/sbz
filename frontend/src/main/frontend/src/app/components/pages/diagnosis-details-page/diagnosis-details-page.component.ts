import {Component, Input, OnInit} from '@angular/core';
import {Diagnosis} from '../../../shared/model/domain/Diagnosis';
import {DiagnosisService} from '../../../core/services/http/diagnosis.service';
import {ActivatedRoute} from '@angular/router';
import {SymptomSharedService} from '../../../core/services/shared/symptom.shared.service';
import {IngredientSharedService} from '../../../core/services/shared/ingredient.shared.service';
import {MedicationSharedService} from '../../../core/services/shared/medication.shared.service';
import {DiseaseSharedService} from '../../../core/services/shared/disease.shared.service';

@Component({
  selector: 'app-diagnosis-details-page',
  templateUrl: './diagnosis-details-page.component.html',
  styleUrls: ['./diagnosis-details-page.component.css'],
  providers: [
    SymptomSharedService,
    IngredientSharedService,
    MedicationSharedService,
    DiseaseSharedService]
})
export class DiagnosisDetailsPageComponent implements OnInit
{
  data: Diagnosis;
  sub: any;

  constructor(private service: DiagnosisService,
              private route: ActivatedRoute)
  {
    this.sub = this.route.params.subscribe(params =>
    {
      let id = +params['id'];
      this.service.getOne(id)
        .subscribe((res: Diagnosis) =>
        {
          this.data = res;
        })
    });
  }

  ngOnInit() {}

}
