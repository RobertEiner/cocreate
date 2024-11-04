import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTextboxComponent } from './edit-textbox.component';

describe('EditTextboxComponent', () => {
  let component: EditTextboxComponent;
  let fixture: ComponentFixture<EditTextboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditTextboxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTextboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
