import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianFormComponent } from './physician-form.component';

describe('PhysicianFormComponent', () => {
  let component: PhysicianFormComponent;
  let fixture: ComponentFixture<PhysicianFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhysicianFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhysicianFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
