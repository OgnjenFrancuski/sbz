import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiseasesPageComponent } from './diseases-page.component';

describe('DiseasesPageComponent', () => {
  let component: DiseasesPageComponent;
  let fixture: ComponentFixture<DiseasesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiseasesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiseasesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
