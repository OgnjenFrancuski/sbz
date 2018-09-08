import {Component, ContentChild, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {SharedService} from '../../../core/services/shared/shared.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Subscription} from 'rxjs/Subscription';
import {Submit} from '../../../shared/model/util/Submit';
import {SubmitType} from '../../../shared/model/util/SubmitType.enum';
import {PhysicianService} from '../../../core/services/http/physician.service';
import {Role} from '../../../shared/model/domain/Role.enum';
import {AuthorityService} from '../../../core/services/security/authority.service';
import {Physician} from '../../../shared/model/domain/Physician';

@Component({
  selector: 'app-physicians-page',
  templateUrl: './physicians-page.component.html',
  styleUrls: ['./physicians-page.component.css'],
  providers: [SharedService]
})
export class PhysiciansPageComponent implements OnInit
{
  data: Physician[];
  modalRef: BsModalRef;
  selected: Physician;

  saveSubscription: Subscription;
  updateSubscription: Subscription;
  clearSubscription: Subscription;

  @ViewChild('formTemplate') formTemplate;


  constructor(private service: PhysicianService,
              private sharedService: SharedService,
              private authorityService: AuthorityService,
              private modalService: BsModalService)
  {
    this.saveSubscription = this.sharedService.getSaveClick()
      .subscribe((res: Submit) =>
      {
        this.saveOrUpdate(res);
      });

    this.updateSubscription = this.sharedService.getUpdateClick()
      .subscribe((res: Physician) =>
      {
        this.openFormModal(this.formTemplate, res);
      });

    this.clearSubscription = this.sharedService.getClearClick()
      .subscribe((res: any) =>
      {
        this.selected = null;
      });
  }


  ngOnInit()
  {
    this.loadPhysicians();
  }


  loadPhysicians()
  {
    this.service.getAll()
      .subscribe((res: Physician[]) =>
      {
        this.data = res;
      });
  }


  openFormModal(template: TemplateRef<any>, physician: Physician)
  {
    this.selected = physician;
    this.modalRef = this.modalService.show(template);
  }


  saveOrUpdate(submit: Submit)
  {
    if (submit.type === SubmitType.CREATE)
      this.service.create(submit.value)
        .subscribe((res: Physician) =>
          {
            this.modalRef.hide();
            this.loadPhysicians();
          }
        );
    else if (submit.type === SubmitType.UPDATE)
      this.service.update(submit.value)
        .subscribe((res: Physician) =>
          {
            this.modalRef.hide();
            this.loadPhysicians();
          }
        );
  }


  isAdmin(): boolean
  {
    return this.authorityService.hasAuthority(Role.ADMIN);
  }

}
