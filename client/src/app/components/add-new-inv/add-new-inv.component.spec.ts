import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewInvComponent } from './add-new-inv.component';

describe('AddNewInvComponent', () => {
  let component: AddNewInvComponent;
  let fixture: ComponentFixture<AddNewInvComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewInvComponent]
    });
    fixture = TestBed.createComponent(AddNewInvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
