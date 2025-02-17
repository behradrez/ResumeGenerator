import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormArray, Validators, FormGroup } from '@angular/forms';
import { ResumeServiceService } from '../resume-service.service';
import {MatInputModule} from "@angular/material/input";
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatButtonToggleChange, MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from '@angular/material/icon';
import { GenerateResumeComponent } from '../generate-resume/generate-resume.component';

@Component({
  selector: 'app-resume-edit-page',
  standalone: true,
  imports: [GenerateResumeComponent,CommonModule,MatButtonModule,MatIconModule, ReactiveFormsModule,MatButtonToggleModule, MatFormFieldModule, MatInputModule],
  template: `
    <div class="container">
      <h2>Create Resume Sections</h2>
      
      <mat-button-toggle-group (change)="onSectionTypeChange($event)" name='SectionType' aria-label="Section Type">
          <mat-button-toggle *ngFor="let type of sectionTypes" [value]="type">{{type | titlecase}}</mat-button-toggle>
      </mat-button-toggle-group>

      <div class="section-form form-container center" >
        <form [formGroup]="currForm" (ngSubmit)="onSubmit()">
          <div *ngFor="let field of getRequiredFields()">
            <div *ngIf="field !== 'bulletpoints'">
              <mat-form-field>
                <mat-label>{{field | titlecase}}</mat-label>
                <input matInput [id]='field' type="text" [formControlName]='field'>
              </mat-form-field>
            </div>              
            <div *ngIf="field === 'bulletpoints'" formArrayName="bulletpoints"> 
            <div class='center'>
              <h3>Bullet Points</h3>
              <button style="max-width: 100%;" mat-fab extended type="button" (click)="addBulletPoint()">
               <mat-icon>add</mat-icon>
              Add a bulletpoint
              </button>
            </div>
            <div>
              <div class='row' *ngFor="let point of bulletPoints.controls; let i=index">
                <textarea style="resize:none;height:60px"[formControlName]="i" matInput placeholder="Ex. Achieved 7000% increase in company revenue"></textarea>
                <button class="deleteButton" mat-mini-fab type="button" (click)="removeBulletPoint(i)">
                  <mat-icon>delete</mat-icon>
                </button>
              </div>
            </div>
          </div>          
          </div>
          <div class="center">
            <button class="save-button" *ngIf="selectedType!= ''" mat-flat-button type="submit">Save Section</button>
          </div>
        </form>
      </div>
      <app-generate-resume></app-generate-resume>
    </div>
  `,
  styleUrl: './section-edit-page.component.scss'
})
export class SectionEditPageComponent {
  fb: FormBuilder = new FormBuilder();
  resumeService = inject(ResumeServiceService);
  currForm: FormGroup = new FormGroup('');

  constructor() {}
  sectionTypes = ['Header','Technical Skills','Education','Experience','Leadership','Project'];  
  selectedType: string = '';
  
  experienceForm = this.fb.group({
    title: ['', Validators.required],
    company: ['', Validators.required],
    location: ['', Validators.required],
    dateRange: ['', Validators.required],
    bulletpoints: this.fb.array([])
  });

  headerForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    linkedInLink: ['', Validators.required],
    githubLink: ['', Validators.required],
    extraInfo: this.fb.array([])
  });
  
  projectForm = this.fb.group({
    name: ['', Validators.required],
    techs: ['', Validators.required],
    bulletpoints: this.fb.array([])
  });
  
  educationForm = this.fb.group({
    degree: ['', Validators.required],
    schoolName: ['', Validators.required],
    dateRange: ['', Validators.required],
    gpa: ['', Validators.required],
    location: ['', Validators.required],
    courses: ['', Validators.required]
  });
  
  technicalForm = this.fb.group({
    languages: ['', Validators.required],
    frameworks: ['', Validators.required],
    devtools: ['', Validators.required]
  });
  
  leadershipForm = this.fb.group({
    position: ['', Validators.required],
    company: ['', Validators.required],
    dateRange: ['', Validators.required],
    location: ['', Validators.required],
    bulletpoints: this.fb.array([])
  });
 
  getRequiredFields(){
    if (!this.currForm) return [];
    return Object.keys(this.currForm.controls);
  }

  onSectionTypeChange(event: MatButtonToggleChange) {
    console.log(event);
    this.selectedType = event.value.toLowerCase();
    switch (this.selectedType) {
      case 'header':
        this.currForm = this.headerForm;
        break;
      case 'experience':
        this.currForm = this.experienceForm;
        break;
      case 'project':
        this.currForm = this.projectForm;
        break;
      case 'education':
        this.currForm = this.educationForm;
        break;
      case 'technical skills':
        this.currForm = this.technicalForm;
        break;
      case 'leadership':
        this.currForm = this.leadershipForm;
        break;
      default:
        this.currForm = new FormGroup({});
        break;
    }
  }
  
  get bulletPoints(): FormArray {
    return this.currForm.get('bulletpoints') as FormArray;
  }
  

  addBulletPoint() {
    const bulletPoints = this.currForm.get('bulletpoints') as FormArray;
    bulletPoints.push(this.fb.control(''));
  }
  
  removeBulletPoint(index: number) {
    this.bulletPoints.removeAt(index);
  }
  
  async onSubmit() {
    const sectionData = this.currForm.value;
    console.log(sectionData);
    
    if (this.selectedType === 'experience') {
      const sectionData = this.experienceForm.value;
      await this.resumeService.saveSection('username', {
        experiences:[
          {
            title: sectionData.title as string,
            company: sectionData.company as string,
            location: sectionData.location as string,
            dateRange: sectionData.dateRange as string,
            bulletpoints: sectionData.bulletpoints as string[]
          }
        ]
      });
    }
  }
}
