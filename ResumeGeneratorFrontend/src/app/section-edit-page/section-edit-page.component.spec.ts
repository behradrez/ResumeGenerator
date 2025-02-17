import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionEditPageComponent } from './section-edit-page.component';

describe('ResumeEditPageComponent', () => {
  let component: SectionEditPageComponent;
  let fixture: ComponentFixture<SectionEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SectionEditPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SectionEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
