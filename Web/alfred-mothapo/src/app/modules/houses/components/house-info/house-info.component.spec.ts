import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseInfoComponent } from './house-info.component';

describe('HouseInfoComponent', () => {
  let component: HouseInfoComponent;
  let fixture: ComponentFixture<HouseInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HouseInfoComponent]
    });
    fixture = TestBed.createComponent(HouseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
