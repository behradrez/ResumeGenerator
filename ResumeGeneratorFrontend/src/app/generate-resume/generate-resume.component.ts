import { Component, inject, SimpleChanges } from '@angular/core';
import { ResumeSectionDTO } from '../interfaces/interfaces';
import { ResumeServiceService } from '../resume-service.service';
import { Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTabChangeEvent, MatTabsModule} from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
@Component({
  selector: 'app-generate-resume',
  standalone:true,
  imports: [CommonModule, MatTabsModule,MatButtonModule,MatIconModule],
  template: `
    <div class="container">
      <h2>Generate Resume</h2>

      <mat-tab-group  class="all-resumes" (selectedTabChange)="newResumeSelected($event)">
          <mat-tab [label]="'Resume '+(i+1)" *ngFor="let resume of resumes; let i=index">
            <mat-tab-group >  
            <mat-tab label='Header' class="header">
                <h3>Header</h3>
                <p>Name: {{resume.header?.[0]?.name}}</p>
                <p>Email: {{resume.header?.[0]?.email}}</p>
                <p>LinkedIn: {{resume.header?.[0]?.linkedInLink}}</p>
                <p>GitHub: {{resume.header?.[0]?.githubLink}}</p>
                <p>Extra provided info: {{resume.header?.[0]?.extraInfo}}</p>
              </mat-tab >
              <mat-tab label="Education" class="education">
                <h3 *ngIf="resume.educationExps?.length">Education</h3>
                <p>School: {{resume.educationExps?.[0]?.schoolName}}</p>
                <p>Degree: {{resume.educationExps?.[0]?.degree}}</p>
                <p>Date Range: {{resume.educationExps?.[0]?.dateRange}}</p>
                <p>GPA: {{resume.educationExps?.[0]?.gpa}}</p>
                <p>Location: {{resume.educationExps?.[0]?.location}}</p>
                <p>Courses: {{resume.educationExps?.[0]?.courses}}</p>
              </mat-tab >
              <mat-tab label="Technical Skills" class="technical">
                <h3>Technical Skills</h3>
                <p >Languages: {{resume.technicalSkills?.[0]?.languages}}</p>
                <p *ngIf="resume.technicalSkills?.length">Technologies: {{resume.technicalSkills?.[0]?.frameworks}}</p>
                <p *ngIf="resume.technicalSkills?.length">Developer Tools: {{resume.technicalSkills?.[0]?.devtools}}</p>
              </mat-tab >
              <mat-tab label="Work Experiences" class="experience">
                <h3>Experience</h3>
                <div *ngFor="let exp of resume.experiences">
                  <p>Company: {{exp?.company}}</p>
                  <p>Position: {{exp?.title}}</p>
                  <p>Date Range: {{exp?.dateRange}}</p>
                  <p>Location: {{exp?.location}}</p>
                  <p *ngFor="let bp of exp.bulletpoints">
                    {{bp}}
                  </p>
                  <hr>
                </div>
              </mat-tab >
              <mat-tab label="Leadership" class="leadership">
                <h3>Leadership</h3>
                <div *ngFor="let lead of resume.leadershipExps">
                  <p>Organization: {{lead?.company}}</p>
                  <p>Position: {{lead?.position}}</p>
                  <p>Date Range: {{lead?.dateRange}}</p>
                  <p>Location: {{lead?.location}}</p>
                  <p *ngFor="let bp of lead.bulletpoints">
                    {{bp}}
                  </p>
                  <hr>
                </div>
              </mat-tab >
              <mat-tab label="Projects" class="projects">
                <h3>Projects</h3>
                <div *ngFor="let proj of resume.projects">
                  <p>Name: {{proj?.name}}</p>
                  <p>Technologies: {{proj?.techs}}</p>
                  <p *ngFor="let bp of proj.bulletpoints">
                    {{bp}}
                  </p>
                  <hr>
                </div>
              </mat-tab >
            </mat-tab-group>

          </mat-tab>
        </mat-tab-group>
        <button mat-fab extended style="margin-top: 10px; background-color:purple">
          <mat-icon>download</mat-icon>
          Download LaTeX Resume
        </button>
    </div>
  `,
  styleUrl: './generate-resume.component.sass'
})
export class GenerateResumeComponent {
  resumes: ResumeSectionDTO[] = [];
  @Input() username = '';
  resumeService = inject(ResumeServiceService);

  selectedResumeId = -1;

  newResumeSelected(event: MatTabChangeEvent){
    this.selectedResumeId = this.resumes[event.index]?.id || -1; 
  }


  async downloadResume(){
    const fileContent = await this.resumeService.getResumeLatexFromID(this.username,this.selectedResumeId.toString());
    const blob = new Blob([fileContent], { type: 'text/plain' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'resume.tex';
    link.click();
    window.URL.revokeObjectURL(url);
  }

  async loadResumes(){
    if(this.username === '') this.resumes = await this.resumeService.getDummyResume();
    else{
      this.resumes = await this.resumeService.getAllResumesByUser(this.username);
    }
    console.log(this.resumes);
  }

  constructor(){
    this.loadResumes();
  }

  ngOnChanges(changes:SimpleChanges){
    if(changes['username']){
      this.loadResumes();
    }
  }
}
