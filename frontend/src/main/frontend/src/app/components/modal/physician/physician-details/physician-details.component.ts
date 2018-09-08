import {Component, Input, OnInit} from '@angular/core';
import {Physician} from '../../../../shared/model/domain/Physician';

@Component({
  selector: 'app-physician-details',
  templateUrl: './physician-details.component.html',
  styleUrls: ['./physician-details.component.css']
})
export class PhysicianDetailsComponent implements OnInit {

  @Input("data")
  physician: Physician;

  constructor() { }

  ngOnInit() {
  }

}
