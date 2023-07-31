import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseHouseComponent } from './base-house.component';

describe('BaseHouseComponent', () => {
  let component: BaseHouseComponent;
  let fixture: ComponentFixture<BaseHouseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BaseHouseComponent]
    });
    fixture = TestBed.createComponent(BaseHouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
