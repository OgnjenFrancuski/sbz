import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianTableComponent } from './physician-table.component';

describe('PhysicianTableComponent', () => {
  let component: PhysicianTableComponent;
  let fixture: ComponentFixture<PhysicianTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhysicianTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhysicianTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
