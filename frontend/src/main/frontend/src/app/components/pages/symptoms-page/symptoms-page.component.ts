import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Role} from '../../../shared/model/domain/Role.enum';
import {ToasterConfig, ToasterService} from 'angular5-toaster/dist';
import {Symptom} from '../../../shared/model/domain/Symptom';
import {SharedService} from '../../../core/services/shared/shared.service';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {SymptomService} from '../../../core/services/http/symptom.service';
import {Submit} from '../../../shared/model/util/Submit';
import {SymptomSharedService} from '../../../core/services/shared/symptom.shared.service';

@Component({
  selector: 'app-symptoms-page',
  templateUrl: './symptoms-page.component.html',
  styleUrls: ['./symptoms-page.component.css'],
  providers: [SymptomSharedService]
})
export class SymptomsPageComponent implements OnInit
{

  data: Symptom[];
  modalRef: BsModalRef;
  selected: Symptom;

  saveSubscription: Subscription;
  updateSubscription: Subscription;
  deleteSubscription: Subscription;
  clearSubscription: Subscription;

  toasterConfig: ToasterConfig;

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: SymptomService,
              private sharedService: SymptomSharedService,
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
      .subscribe((res: Symptom) =>
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
    this.loadSymptoms();
  }


  loadSymptoms()
  {
    this.service.getAll()
      .subscribe((res: Symptom[]) =>
      {
        this.data = res;
      });
  }


  openFormModal(template: TemplateRef<any>, symptom: Symptom)
  {
    this.selected = symptom;
    this.modalRef = this.modalService.show(template);
  }


  saveOrUpdate(submit: Submit)
  {
    if (submit.type === SubmitType.CREATE)
      this.service.create(submit.value)
        .subscribe((res: Symptom) =>
          {
            this.modalRef.hide();
            this.loadSymptoms();
          }
        );
    else if (submit.type === SubmitType.UPDATE)
      this.service.update(submit.value)
        .subscribe((res: Symptom) =>
          {
            this.modalRef.hide();
            this.loadSymptoms();
          }
        );
  }


  delete(id: any)
  {
    this.service.delete(id)
      .subscribe((res: any) =>
        {
          this.loadSymptoms();
        },
        (error: any) =>
        {
          this.toasterService.pop('error', 'Error', "Can't delete a symptom that is part of disease.");
        })
  }


  isAdmin(): boolean
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }

}
