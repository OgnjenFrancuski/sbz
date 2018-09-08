import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Role} from '../../../shared/model/domain/Role.enum';
import {SharedService} from '../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {IngredientService} from '../../../core/services/http/ingredient.service';
import {Submit} from '../../../shared/model/util/Submit';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {Ingredient} from '../../../shared/model/domain/Ingredient';
import {BadRequestError} from '../../../shared/errors/bad-request-error';
import {AppError} from '../../../shared/errors/app-error';
import {ToasterConfig, ToasterService} from 'angular5-toaster/dist';
import {IngredientSharedService} from '../../../core/services/shared/ingredient.shared.service';

@Component({
  selector: 'app-ingredients-page',
  templateUrl: './ingredients-page.component.html',
  styleUrls: ['./ingredients-page.component.css'],
  providers: [IngredientSharedService]
})
export class IngredientsPageComponent implements OnInit
{
  data: Ingredient[];
  modalRef: BsModalRef;
  selected: Ingredient;

  saveSubscription: Subscription;
  updateSubscription: Subscription;
  deleteSubscription: Subscription;
  clearSubscription: Subscription;

  toasterConfig: ToasterConfig;

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: IngredientService,
              private sharedService: IngredientSharedService,
              private authorityService: AuthorityService,
              private toasterService: ToasterService,
              private modalService: BsModalService)
  {
    this.saveSubscription = this.sharedService.getSaveClick()
      .subscribe((res: Submit) =>
      {
        this.saveOrUpdate(res);
      });

    this.updateSubscription = this.sharedService.getUpdateClick()
      .subscribe((res: Ingredient) =>
      {
        this.openFormModal(this.formTemplate, res);
      });

    this.deleteSubscription = this.sharedService.getDeleteClick()
      .subscribe((res: any) =>
      {
        this.delete(res);
      });

    this.clearSubscription = this.sharedService.getClearClick()
      .subscribe((res: any) =>
      {
        this.selected = null;
      });

    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }


  ngOnInit()
  {
    this.loadIngredients();
  }


  loadIngredients()
  {
    this.service.getAll()
      .subscribe((res: Ingredient[]) =>
      {
        this.data = res;
      });
  }


  openFormModal(template: TemplateRef<any>, ingredient: Ingredient)
  {
    this.selected = ingredient;
    this.modalRef = this.modalService.show(template);
  }


  saveOrUpdate(submit: Submit)
  {
    if (submit.type === SubmitType.CREATE)
      this.service.create(submit.value)
        .subscribe((res: Ingredient) =>
          {
            this.modalRef.hide();
            this.loadIngredients();
          }
        );
    else if (submit.type === SubmitType.UPDATE)
      this.service.update(submit.value)
        .subscribe((res: Ingredient) =>
          {
            this.modalRef.hide();
            this.loadIngredients();
          }
        );
  }


  delete(id: any)
  {
    this.service.delete(id)
      .subscribe((res: any) =>
      {
        this.loadIngredients();
      },
      (error: any) =>
      {
        this.toasterService.pop('error', 'Error', "Can't delete an ingredient that is part of existing medication or recorded as allergen for patient.");
      })
  }


  isAdmin(): boolean
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }

}
