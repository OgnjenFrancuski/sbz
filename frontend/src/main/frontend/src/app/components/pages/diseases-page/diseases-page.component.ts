import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Role} from '../../../shared/model/domain/Role.enum';
import {SharedService} from '../../../core/services/shared/shared.service';
import {Disease} from '../../../shared/model/domain/Disease';
import {Subscription} from 'rxjs/Subscription';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {DiseaseService} from '../../../core/services/http/disease.service';
import {Submit} from '../../../shared/model/util/Submit';
import {Symptom} from '../../../shared/model/domain/Symptom';
import {SymptomService} from '../../../core/services/http/symptom.service';
import {SymptomSharedService} from '../../../core/services/shared/symptom.shared.service';
import {DiseaseSharedService} from '../../../core/services/shared/disease.shared.service';


@Component({
  selector: 'app-diseases-page',
  templateUrl: './diseases-page.component.html',
  styleUrls: ['./diseases-page.component.css'],
  providers: [DiseaseSharedService, SymptomSharedService]
})
export class DiseasesPageComponent implements OnInit {

  data: Disease[];
  modalRef: BsModalRef;
  selected: Disease;

  saveSubscription: Subscription;
  updateSubscription: Subscription;
  deleteSubscription: Subscription;
  clearSubscription: Subscription;

  symptoms: Symptom[];

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: DiseaseService,
              private symptomService: SymptomService,
              private authorityService: AuthorityService,
              private sharedService: DiseaseSharedService,
              private modalService: BsModalService)
  {
    this.saveSubscription = this.sharedService.getSaveClick()
      .subscribe((res: Submit) =>
      {
        this.saveOrUpdate(res);
      });

    this.updateSubscription = this.sharedService.getUpdateClick()
      .subscribe((res: Disease) =>
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
    this.loadSymptoms();
    this.loadDiseases();
  }


  loadDiseases()
  {
    this.service.getAll()
      .subscribe((res: Disease[]) =>
      {
        this.data = res;
      });
  }


  loadSymptoms()
  {
    this.symptomService.getAll()
      .subscribe((res: Symptom[]) =>
      {
        this.symptoms = res;
      })
  }


  openFormModal(template: TemplateRef<any>, med: Disease)
  {
    this.selected = med;
    this.modalRef = this.modalService.show(template);
  }


  delete(id: any)
  {
    this.service.delete(id)
      .subscribe((res: any) =>
      {
        this.loadDiseases();
      })
  }


  saveOrUpdate(submit: Submit)
  {
    if (submit.type === SubmitType.CREATE)
      this.service.create(submit.value)
        .subscribe((res: Disease) =>
          {
            this.modalRef.hide();
            this.loadDiseases();
          }
        );
    else if (submit.type === SubmitType.UPDATE)
      this.service.update(submit.value)
        .subscribe((res: Disease) =>
          {
            this.modalRef.hide();
            this.loadDiseases();
          }
        );
  }


  isAdmin(): boolean
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }

}
