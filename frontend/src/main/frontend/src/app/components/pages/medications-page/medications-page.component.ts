import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {SharedService} from '../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {Submit} from '../../../shared/model/util/Submit';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {Role} from '../../../shared/model/domain/Role.enum';
import {IngredientService} from '../../../core/services/http/ingredient.service';
import {Ingredient} from '../../../shared/model/domain/Ingredient';
import {Medication} from '../../../shared/model/domain/Medication';
import {MedicationService} from '../../../core/services/http/medication.service';
import {MedicationSharedService} from '../../../core/services/shared/medication.shared.service';
import {IngredientSharedService} from '../../../core/services/shared/ingredient.shared.service';

@Component({
  selector: 'app-medications-page',
  templateUrl: './medications-page.component.html',
  styleUrls: ['./medications-page.component.css'],
  providers: [MedicationSharedService, IngredientSharedService]
})
export class MedicationsPageComponent implements OnInit {

  data: Medication[];
  modalRef: BsModalRef;
  selected: Medication;

  saveSubscription: Subscription;
  updateSubscription: Subscription;
  deleteSubscription: Subscription;
  clearSubscription: Subscription;

  ingredients: Ingredient[];

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: MedicationService,
              private ingredientService: IngredientService,
              private authorityService: AuthorityService,
              private sharedService: MedicationSharedService,
              private modalService: BsModalService)
  {
    this.saveSubscription = this.sharedService.getSaveClick()
      .subscribe((res: Submit) =>
      {
        this.saveOrUpdate(res);
      });

    this.updateSubscription = this.sharedService.getUpdateClick()
      .subscribe((res: Medication) =>
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
        this.selected = null;
      });
  }


  ngOnInit()
  {
    this.loadIngredients();
    this.loadMedications();
  }


  loadMedications()
  {
    this.service.getAll()
      .subscribe((res: Medication[]) =>
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


  openFormModal(template: TemplateRef<any>, med: Medication)
  {
    this.selected = med;
    this.modalRef = this.modalService.show(template);
  }


  delete(id: any)
  {
    this.service.delete(id)
      .subscribe((res: any) =>
      {
        this.loadMedications();
      })
  }


  saveOrUpdate(submit: Submit)
  {
    if (submit.type === SubmitType.CREATE)
      this.service.create(submit.value)
        .subscribe((res: Medication) =>
          {
            this.modalRef.hide();
            this.loadMedications();
          }
        );
    else if (submit.type === SubmitType.UPDATE)
      this.service.update(submit.value)
        .subscribe((res: Medication) =>
          {
            this.modalRef.hide();
            this.loadMedications();
          }
        );
  }


  isAdmin(): boolean
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }

}
