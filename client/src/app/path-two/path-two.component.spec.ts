import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PathTwoComponent } from './path-two.component';

describe('PathTwoComponent', () => {
  let component: PathTwoComponent;
  let fixture: ComponentFixture<PathTwoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PathTwoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PathTwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
