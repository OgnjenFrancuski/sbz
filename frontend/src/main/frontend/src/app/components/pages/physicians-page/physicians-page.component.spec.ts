import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysiciansPageComponent } from './physicians-page.component';

describe('PhysiciansPageComponent', () => {
  let component: PhysiciansPageComponent;
  let fixture: ComponentFixture<PhysiciansPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhysiciansPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhysiciansPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
