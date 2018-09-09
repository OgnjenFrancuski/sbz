import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
// guard
import { AuthGuard } from "./guards/auth.guard";
import { AnonymusGuard } from "./guards/anonymus.guard";
// service
import { AuthService } from "./services/security/auth.service";
import { AuthorityService } from './services/security/authority.service';
import {PatientService} from './services/http/patient.service';
import {IngredientService} from './services/http/ingredient.service';
import {PhysicianService} from './services/http/physician.service';
import {MedicationService} from './services/http/medication.service';
import {SymptomService} from './services/http/symptom.service';
import {DiseaseService} from './services/http/disease.service';
import {KieService} from './services/http/kie.service';
import {DiagnosisService} from './services/http/diagnosis.service';


@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    AuthService,
    AuthorityService,
    AnonymusGuard,
    AuthGuard,
    PatientService,
    PhysicianService,
    MedicationService,
    SymptomService,
    DiseaseService,
    IngredientService,
    DiagnosisService,
    KieService,
  ]
})
export class CoreModule {
  constructor (@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }
}
