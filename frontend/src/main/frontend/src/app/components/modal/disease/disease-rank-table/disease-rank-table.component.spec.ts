import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiseaseRankTableComponent } from './disease-rank-table.component';

describe('DiseaseRankTableComponent', () => {
  let component: DiseaseRankTableComponent;
  let fixture: ComponentFixture<DiseaseRankTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiseaseRankTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiseaseRankTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
