import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HTTP_INTERCEPTORS } from "@angular/common/http";

// module
import { AppRouterModule } from "./app-router.module";
import { SharedModule } from "./shared/shared.module";
import { CoreModule } from "./core/core.module";
import {ModalModule, TypeaheadModule} from 'ngx-bootstrap';
import { ArchwizardModule } from 'angular-archwizard';


// error handler
import { AppErrorHandler } from "./core/error-handlers/app-error-handler";

// component
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/modal/navbar/navbar.component';
import { IngredientTableComponent } from './components/modal/ingredient/ingredient-table/ingredient-table.component';
import { IngredientFormComponent } from './components/modal/ingredient/ingredient-form/ingredient-form.component';
import { SymptomFormComponent } from './components/modal/symptom/symptom-form/symptom-form.component';
import { SymptomTableComponent } from './components/modal/symptom/symptom-table/symptom-table.component';
import { DiseaseTableComponent } from './components/modal/disease/disease-table/disease-table.component';
import { DiagnosisTableComponent } from './components/modal/diagnosis/diagnosis-table/diagnosis-table.component';
import { MedicationTableComponent } from './components/modal/medication/medication-table/medication-table.component';
import { MedicationFormComponent } from './components/modal/medication/medication-form/medication-form.component';
import { PatientFormComponent } from './components/modal/patient/patient-form/patient-form.component';
import { PatientTableComponent } from './components/modal/patient/patient-table/patient-table.component';
import { PhysicianTableComponent } from './components/modal/physician/physician-table/physician-table.component';
import { PhysicianFormComponent } from './components/modal/physician/physician-form/physician-form.component';
import { DiagnosisFormComponent } from './components/modal/diagnosis/diagnosis-form/diagnosis-form.component';
import { LoginComponent } from './components/pages/login/login.component';
import { PatientsPageComponent } from './components/pages/patients-page/patients-page.component';
import { PhysiciansPageComponent } from './components/pages/physicians-page/physicians-page.component';
import { IngredientsPageComponent } from './components/pages/ingredients-page/ingredients-page.component';
import { MedicationsPageComponent } from './components/pages/medications-page/medications-page.component';
import { DiseasesPageComponent } from './components/pages/diseases-page/diseases-page.component';
import { SymptomsPageComponent } from './components/pages/symptoms-page/symptoms-page.component';
import { DiseaseFormComponent } from './components/modal/disease/disease-form/disease-form.component';
import { DiagnosisPageComponent } from './components/pages/diagnosis-page/diagnosis-page.component';
import { PatientDetailsComponent } from './components/modal/patient/patient-details/patient-details.component';
import { DiagnosisDetailsComponent } from './components/modal/diagnosis/diagnosis-details/diagnosis-details.component';
import { PhysicianDetailsComponent } from './components/modal/physician/physician-details/physician-details.component';
import {NgAutoCompleteModule} from 'ng-auto-complete';
import { DiseaseRankTableComponent } from './components/modal/disease/disease-rank-table/disease-rank-table.component';
import { DiagnosesPageComponent } from './components/pages/diagnoses-page/diagnoses-page.component';
import { DiagnosisDetailsPageComponent } from './components/pages/diagnosis-details-page/diagnosis-details-page.component';
import { ReportPageComponent } from './components/pages/report-page/report-page.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    IngredientTableComponent,
    IngredientFormComponent,
    SymptomFormComponent,
    SymptomTableComponent,
    DiseaseTableComponent,
    DiagnosisTableComponent,
    MedicationTableComponent,
    MedicationFormComponent,
    PatientFormComponent,
    PatientTableComponent,
    PhysicianTableComponent,
    PhysicianFormComponent,
    DiagnosisFormComponent,
    LoginComponent,
    PatientsPageComponent,
    PhysiciansPageComponent,
    IngredientsPageComponent,
    MedicationsPageComponent,
    DiseasesPageComponent,
    SymptomsPageComponent,
    DiseaseFormComponent,
    DiagnosisPageComponent,
    PatientDetailsComponent,
    DiagnosisDetailsComponent,
    PhysicianDetailsComponent,
    DiseaseRankTableComponent,
    DiagnosesPageComponent,
    DiagnosisDetailsPageComponent,
    ReportPageComponent,
  ],
  imports: [
    BrowserModule,
    NgAutoCompleteModule,
    ArchwizardModule,
    BrowserAnimationsModule,
    NgbModule.forRoot(),
    ModalModule.forRoot(),
    TypeaheadModule.forRoot(),
    SharedModule,
    CoreModule,
    AppRouterModule
  ],
  providers: [
    {
      provide: ErrorHandler,
      useClass: AppErrorHandler
    },
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
  ],
  entryComponents: [],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
