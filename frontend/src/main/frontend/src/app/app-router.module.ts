import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
// component
import { LoginComponent } from "./components/pages/login/login.component";
import {PatientsPageComponent} from './components/pages/patients-page/patients-page.component';
import {PhysiciansPageComponent} from './components/pages/physicians-page/physicians-page.component';
import {MedicationsPageComponent} from './components/pages/medications-page/medications-page.component';
import {DiseasesPageComponent} from './components/pages/diseases-page/diseases-page.component';
import {IngredientsPageComponent} from './components/pages/ingredients-page/ingredients-page.component';
import {SymptomsPageComponent} from './components/pages/symptoms-page/symptoms-page.component';
import {DiagnosisPageComponent} from './components/pages/diagnosis-page/diagnosis-page.component';
import {DiagnosesPageComponent} from './components/pages/diagnoses-page/diagnoses-page.component';
import {DiagnosisDetailsPageComponent} from './components/pages/diagnosis-details-page/diagnosis-details-page.component';
import {ReportPageComponent} from './components/pages/report-page/report-page.component';
import {AuthGuard} from './core/guards/auth.guard';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'patients', component: PatientsPageComponent, canActivate: [AuthGuard] },
  { path: 'physicians', component: PhysiciansPageComponent, canActivate: [AuthGuard] },
  { path: 'medications', component: MedicationsPageComponent,canActivate: [AuthGuard] },
  { path: 'ingredients', component: IngredientsPageComponent,canActivate: [AuthGuard] },
  { path: 'diseases', component: DiseasesPageComponent,canActivate: [AuthGuard] },
  { path: 'symptoms', component: SymptomsPageComponent, canActivate: [AuthGuard] },
  { path: 'diagnosis', component: DiagnosisPageComponent, canActivate: [AuthGuard] },
  { path: 'diagnoses', component: DiagnosesPageComponent, canActivate: [AuthGuard] },
  { path: 'diagnoses/:id', component: DiagnosisDetailsPageComponent, canActivate: [AuthGuard] },
  { path: 'report', component: ReportPageComponent },
  { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [RouterModule]
})
export class AppRouterModule { }
