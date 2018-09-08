import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiagnosesPageComponent } from './diagnoses-page.component';

describe('DiagnosesPageComponent', () => {
  let component: DiagnosesPageComponent;
  let fixture: ComponentFixture<DiagnosesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiagnosesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiagnosesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
