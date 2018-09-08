import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Disease} from '../../../../shared/model/domain/Disease';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {DiseaseRank} from '../../../../shared/model/domain/DiseaseRank';

@Component({
  selector: 'app-disease-symptom-count-table',
  templateUrl: './disease-rank-table.component.html',
  styleUrls: ['./disease-rank-table.component.css']
})
export class DiseaseRankTableComponent implements OnInit {

  @Input("data")
  data: DiseaseRank[];

  constructor() { }

  ngOnInit() {}


  getSortedList(): DiseaseRank[]
  {
    this.data.sort((a: DiseaseRank, b: DiseaseRank) =>
    {
      if (a.count > b.count) return -1;
      if (a.count < b.count) return 1;
      return 0;
    });

    return this.data;
  }
}
