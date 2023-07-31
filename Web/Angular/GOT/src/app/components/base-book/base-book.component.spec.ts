import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseBookComponent } from './base-book.component';

describe('BaseBookComponent', () => {
  let component: BaseBookComponent;
  let fixture: ComponentFixture<BaseBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BaseBookComponent]
    });
    fixture = TestBed.createComponent(BaseBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
