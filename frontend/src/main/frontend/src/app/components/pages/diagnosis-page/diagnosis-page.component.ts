import {Component, ElementRef, OnInit, QueryList, TemplateRef, ViewChild} from '@angular/core';
import {Symptom} from '../../../shared/model/domain/Symptom';
import {Medication} from '../../../shared/model/domain/Medication';
import {Diagnosis} from '../../../shared/model/domain/Diagnosis';
import {Patient} from '../../../shared/model/domain/Patient';
import {Role} from '../../../shared/model/domain/Role.enum';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {IngredientService} from '../../../core/services/http/ingredient.service';
import {SharedService} from '../../../core/services/shared/shared.service';
import {PatientService} from '../../../core/services/http/patient.service';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {SymptomService} from '../../../core/services/http/symptom.service';
import {MedicationService} from '../../../core/services/http/medication.service';
import {DiseaseService} from '../../../core/services/http/disease.service';
import {Subscription} from 'rxjs/Subscription';
import {PatientSharedService} from '../../../core/services/shared/patient.shared.service';
import {MedicationSharedService} from '../../../core/services/shared/medication.shared.service';
import {SymptomSharedService} from '../../../core/services/shared/symptom.shared.service';
import {IngredientSharedService} from '../../../core/services/shared/ingredient.shared.service';
import {Disease} from '../../../shared/model/domain/Disease';
import {DiseaseSharedService} from '../../../core/services/shared/disease.shared.service';
import {KieService} from '../../../core/services/http/kie.service';
import {Physician} from '../../../shared/model/domain/Physician';
import {CreateNewAutocompleteGroup, NgAutocompleteComponent, SelectedAutocompleteItem} from 'ng-auto-complete';
import {DiseaseRank} from '../../../shared/model/domain/DiseaseRank';
import {ToasterConfig, ToasterService} from 'angular5-toaster/dist';
import {DiagnosisService} from '../../../core/services/http/diagnosis.service';
import {Router} from '@angular/router';
import {PhysicianService} from '../../../core/services/http/physician.service';

@Component({
  selector: 'app-diagnosis-page',
  templateUrl: './diagnosis-page.component.html',
  styleUrls: ['./diagnosis-page.component.css'],
  providers: [
    PatientSharedService,
    SymptomSharedService,
    MedicationSharedService,
    IngredientSharedService,
    DiseaseSharedService,
    SharedService
  ]
})
export class DiagnosisPageComponent implements OnInit {

  patients: Patient[];
  selectedPatient: Patient;

  physician: Physician;

  diagnosis: Diagnosis;

  bodyTemperature: number;

  symptoms: Symptom[];
  selectedSymptoms: Symptom[];

  medications: Medication[];
  selectedMedications: Medication[];
  allergicMedications: Medication[];
  medicationIsValid: boolean;

  diseases: Disease[];
  selectedDiseases: Disease[];

  diseasesNames: string[];
  selectedDiseaseName: string;
  diseaseSortedSymptoms: Disease;
  diseaseRanking: DiseaseRank[];

  modalRef: BsModalRef;
  toasterConfig: ToasterConfig;

  selectPatientSubscription: Subscription;
  addSymptomSubscription: Subscription;
  removeSymptomSubscription: Subscription;
  addMedicationSubscription: Subscription;
  removeMedicationSubscription: Subscription;
  addDiseaseSubscription: Subscription;
  removeDiseaseSubscription: Subscription;


  constructor(private patientService: PatientService,
              private physicianService: PhysicianService,
              private symptomService: SymptomService,
              private diseaseService: DiseaseService,
              private medicationService: MedicationService,
              private diagnosisService: DiagnosisService,
              private authorityService: AuthorityService,
              private kieService: KieService,
              private patientSharedService: PatientSharedService,
              private symptomSharedService: SymptomSharedService,
              private medicationSharedService: MedicationSharedService,
              private diseaseSharedService: DiseaseSharedService,
              private modalService: BsModalService,
              private toasterService: ToasterService,
              private router: Router)
  {
    this.selectedSymptoms = [];
    this.selectedMedications = [];
    this.allergicMedications = [];
    this.selectedDiseases = [];
    this.medicationIsValid = false;
    this.diseasesNames = [];
    this.diseaseRanking = [];
    this.toasterConfig = new ToasterConfig({timeout: 4000});

    this.selectPatientSubscription = this.patientSharedService.getSelectClick()
      .subscribe((res: Patient) =>
      {
        this.selectedPatient = res;
      });

    this.addSymptomSubscription = this.symptomSharedService.getAddClick()
      .subscribe((res: Symptom) =>
      {
        this.addItem(res.id, 'SYMPTOM');
      });

    this.removeSymptomSubscription = this.symptomSharedService.getRemoveClick()
      .subscribe((res: Symptom) =>
      {
        this.removeItem(res.id, 'SYMPTOM');
      });

    this.addMedicationSubscription = this.medicationSharedService.getAddClick()
      .subscribe((res: Medication) =>
      {
        this.addItem(res.id, 'MEDICATION');
      });

    this.removeMedicationSubscription = this.medicationSharedService.getRemoveClick()
      .subscribe((res: Medication) =>
      {
        this.removeItem(res.id, 'MEDICATION');
      });

    this.addDiseaseSubscription = this.diseaseSharedService.getAddClick()
      .subscribe((res: Disease) =>
      {
        this.addItem(res.id, 'DISEASE');
      });

    this.removeDiseaseSubscription = this.diseaseSharedService.getRemoveClick()
      .subscribe((res: Disease) =>
      {
        this.removeItem(res.id, 'DISEASE');
      });

    this.loadPatients();
    this.loadPhysician();
    this.loadSymptoms();
    this.loadMedications();
    this.loadDiseases();
  }


  ngOnInit() {}


  loadPatients()
  {
    this.patientService.getAll()
      .subscribe((res: Patient[]) =>
      {
        this.patients = res;
      });
  }


  loadPhysician()
  {
    this.physicianService.findByUsername()
      .subscribe((res: Physician) =>
      {
        this.physician = res;
      })
  }


  loadSymptoms()
  {
    this.symptomService.getAll()
      .subscribe((res: Symptom[]) =>
      {
        this.symptoms = res;
      });
  }


  loadMedications()
  {
    this.medicationService.getAll()
      .subscribe((res: Medication[]) =>
      {
        this.medications = res;
      });
  }


  loadDiseases()
  {
    this.diseaseService.getAll()
      .subscribe((res: Disease[]) =>
      {
        this.diseases = res;
        for (let d of res)
          this.diseasesNames.push(d.name);
      });
  }


  runDiagnostics()
  {
    let d = new Diagnosis();
    d.patient = this.selectedPatient;
    d.bodyTemperature = this.bodyTemperature;
    d.symptoms = this.selectedSymptoms;
    d.physician = new Physician();
    d.diseases = [];
    d.prescribedMedications = [];
    this.kieService.runDiagnostics(d)
      .subscribe((res: Disease[]) =>
      {
        this.selectedDiseases = res;
        if (res.length < 1)
          this.toasterService.pop('warning', 'WARNING', "Diagnostic found no diseases for entered symptoms.");
        else
          this.toasterService.pop('success', 'SUCCESS', "Diagnostic successful.");
      })
  }


  validateDiagnostics()
  {
    let d = new Diagnosis();
    d.patient = this.selectedPatient;
    d.physician = new Physician();
    d.symptoms = [];
    d.diseases = [];
    d.prescribedMedications = this.selectedMedications;
    this.kieService.validateDiagnostics(d)
      .subscribe((res: Medication[]) =>
      {
        this.allergicMedications = res;
        if (res.length === 0)
          this.medicationIsValid = true;

        if (res.length < 1)
          this.toasterService.pop('success', 'SUCCESS', "Prescribed medications is valid.");
        else
          this.toasterService.pop('warning', 'WARNING', "Prescribed medication is not valid. Patient is allergic to medications / ingredients as show in table.");

      });

  }


  showSymptoms()
  {
    this.kieService.getSymptomsSortedByImportance(this.selectedDiseaseName)
      .subscribe((res: Disease) =>
      {
        this.diseaseSortedSymptoms = res;
      })
  }


  showDiseasesRanking()
  {
    let d = new Diagnosis();
    d.patient = this.selectedPatient;
    d.physician = new Physician();
    d.symptoms = this.selectedSymptoms;
    d.diseases = [];
    d.prescribedMedications = [];
    this.kieService.getDiseaseRankingBySymptoms(d)
      .subscribe((res: DiseaseRank[]) =>
      {
        console.log(res);
        this.diseaseRanking = res;
      });
  }


  resetDiseaseRanking()
  {
    this.diseaseRanking = [];
  }


  resetSymptoms()
  {
    this.selectedDiseaseName = null;
    this.diseaseSortedSymptoms = null;
  }


  openTemplate(template: TemplateRef<any>, data: any)
  {
    this.modalRef = this.modalService.show(template, data);
  }


  confirmTemperature()
  {
    for (let s of this.symptoms)
    {
      if (s.name === 'body temperature' && this.selectedSymptoms.indexOf(s) === -1)
      {
        this.selectedSymptoms.push(s);
        break;
      }
    }
  }


  resetTemperature()
  {
    for (let s of this.selectedSymptoms)
    {
      if (s.name === 'body temperature')
      {
        this.bodyTemperature = null;
        this.selectedSymptoms.splice(this.selectedSymptoms.indexOf(s), 1);
        break;
      }
    }
  }


  prepareDiagnosis()
  {
    this.diagnosis = new Diagnosis();
    this.diagnosis.patient = this.selectedPatient;
    this.diagnosis.symptoms = this.selectedSymptoms;
    this.diagnosis.diseases = this.selectedDiseases;
    this.diagnosis.prescribedMedications = this.selectedMedications;
    this.diagnosis.bodyTemperature = this.bodyTemperature;
    this.diagnosis.physician = this.physician;
    this.diagnosis.date = new Date();
  }


  submit()
  {
    this.prepareDiagnosis();

    this.diagnosisService.create(this.diagnosis)
      .subscribe((res: Diagnosis) =>
      {
        this.router.navigate(['/diagnoses', res.id]);
      });
  }


  addItem(id: any, type: string)
  {
    let item = null;
    let selectedList = []; //type == 'SYMPTOM' ? this.selectedSymptoms : this.selectedMedications;
    let traverseList = []; //type == 'SYMPTOM' ? this.symptoms : this.medications;
    let found = false;

    if (type === 'SYMPTOM')
    {
      selectedList = this.selectedSymptoms;
      traverseList = this.symptoms;
    }
    else if (type === 'MEDICATION')
    {
      this.medicationIsValid = false;
      selectedList = this.selectedMedications;
      traverseList = this.medications;
    }
    else
    {
      selectedList = this.selectedDiseases;
      traverseList = this.diseases;
    }

    for (let i of traverseList)
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
    let selectedList = [];
    let traverseList = [];

    if (type === 'SYMPTOM')
    {
      selectedList = this.selectedSymptoms;
      traverseList = this.symptoms;
    }
    else if (type === 'MEDICATION')
    {
      selectedList = this.selectedMedications;
      traverseList = this.medications;
    }
    else
    {
      selectedList = this.selectedDiseases;
      traverseList = this.diseases;
    }

    for (let i of traverseList)
    {
      if (i.id === id)
      {
        let idx = selectedList.indexOf(i);
        selectedList.splice(idx, 1);
        if (type === 'SYMPTOM' && i.name === 'body temperature')
          this.bodyTemperature = null;
        break;
      }
    }
  }


  isPhysician()
  {
    return this.authorityService.hasAuthority(Role.PHYSICIAN);
  }

}
