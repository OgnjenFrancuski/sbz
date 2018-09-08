import {Component, ContentChild, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Patient} from '../../../shared/model/domain/Patient';
import {SharedService} from '../../../core/services/shared/shared.service';
import {PatientService} from '../../../core/services/http/patient.service';
import {Ingredient} from '../../../shared/model/domain/Ingredient';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Subscription} from 'rxjs/Subscription';
import {Submit} from '../../../shared/model/util/Submit';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {IngredientService} from '../../../core/services/http/ingredient.service';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {Role} from '../../../shared/model/domain/Role.enum';
import {PatientSharedService} from '../../../core/services/shared/patient.shared.service';
import {IngredientSharedService} from '../../../core/services/shared/ingredient.shared.service';

@Component({
  selector: 'app-patients-page',
  templateUrl: './patients-page.component.html',
  styleUrls: ['./patients-page.component.css'],
  providers: [PatientSharedService, IngredientSharedService]
})
export class PatientsPageComponent implements OnInit {

  data: Patient[];
  modalRef: BsModalRef;
  selectedPatient: Patient;

  saveSubscription: Subscription;
  updateSubscription: Subscription;
  deleteSubscription: Subscription;
  clearSubscription: Subscription;

  ingredients: Ingredient[];

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: PatientService,
              private ingredientService: IngredientService,
              private authorityService: AuthorityService,
              private sharedService: PatientSharedService,
              private modalService: BsModalService)
  {
    this.saveSubscription = this.sharedService.getSaveClick()
      .subscribe((res: Submit) =>
      {
        this.saveOrUpdate(res);
      });

    this.updateSubscription = this.sharedService.getUpdateClick()
      .subscribe((res: Patient) =>
      {
        this.openFormModal(this.formTemplate, res);
      });

    this.deleteSubscription = this.sharedService.getDeleteClick()
      .subscribe((res: any) =>
      {
        this.delete(res)
      });

    this.clearSubscription = this.sharedService.getClearClick()
      .subscribe((res: any) =>
      {
        this.selectedPatient = null;
      });
  }


  ngOnInit()
  {
    this.loadIngredients();
    this.loadPatients();
  }


  loadPatients()
  {
    this.service.getAll()
      .subscribe((res: Patient[]) =>
      {
        this.data = res;
      });
  }


  loadIngredients()
  {
    this.ingredientService.getAll()
      .subscribe((res: Ingredient[]) =>
      {
        this.ingredients = res;
      })
  }


  openFormModal(template: TemplateRef<any>, patient: Patient)
  {
    this.selectedPatient = patient;
    this.modalRef = this.modalService.show(template);
  }


  delete(id: any)
  {
    this.service.delete(id)
      .subscribe((res: any) =>
      {
        this.loadPatients();
      })
  }


  saveOrUpdate(submit: Submit)
  {
    if (submit.type === SubmitType.CREATE)
      this.service.create(submit.value)
        .subscribe((res: Patient) =>
        {
          this.modalRef.hide();
          this.loadPatients();
        }
      );
    else if (submit.type === SubmitType.UPDATE)
      this.service.update(submit.value)
        .subscribe((res: Patient) =>
          {
            this.modalRef.hide();
            this.loadPatients();
          }
        );
  }


  isAdmin(): boolean
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }

}
