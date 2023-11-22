import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateInvComponent } from './update-inv.component';

describe('UpdateInvComponent', () => {
  let component: UpdateInvComponent;
  let fixture: ComponentFixture<UpdateInvComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateInvComponent]
    });
    fixture = TestBed.createComponent(UpdateInvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
