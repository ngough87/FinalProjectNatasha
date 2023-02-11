import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WalkPageComponent } from './walk-page.component';

describe('WalkPageComponent', () => {
  let component: WalkPageComponent;
  let fixture: ComponentFixture<WalkPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WalkPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WalkPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
