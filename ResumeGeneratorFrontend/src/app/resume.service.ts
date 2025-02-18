import { Injectable } from '@angular/core';
import { ResumeSectionDTO } from './interfaces/interfaces';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  url = process.env['BACKEND_URL'] ?? "http://localhost:8080/"; 

  async getAllResumesByUser(username:String){
    const req = await fetch(this.url+username+"resumes");
    return await req.json();
  }

  async getAllResumeSections(username:String){
    const req = await fetch(this.url+username);
    return await req.json();
  }

  async saveResume(username:String, resume: ResumeSectionDTO){
    const req = await fetch(this.url+username+"resume",
      {
        method:"POST",
        body: JSON.stringify(resume)
      }
    )
    return await req.json();
  }

  async saveSection(username: String, section: ResumeSectionDTO){
    const req = await fetch(this.url+username+"resume/section")
  }

  async getResumeLatexFromID(username:string, resumeId:string){
    const req = await fetch(this.url+resumeId+"generate");
    return await req.json();
  }

  async getResumeLatexFromSections(sections:ResumeSectionDTO){
    const req = await fetch(this.url+"resume/generate",
      {
        body: JSON.stringify(sections)
      }
    );
    return await req.json();

  }


  async getDummyResume(){
    const resumeSection: ResumeSectionDTO[] = [{
      educationExps: [
        {
          id: 1,
          degree: 'Bachelor of Science in Computer Science',
          schoolName: 'University of Example',
          dateRange: '2015 - 2019',
          gpa: '3.8',
          location: 'Example City',
          courses: 'Algorithms, Data Structures, Operating Systems'
        }
      ],
      header: [
        {
          id: 1,
          name: 'John Doe',
          email: 'john.doe@example.com',
          linkedInLink: 'https://www.linkedin.com/in/johndoe',
          githubLink: 'https://github.com/johndoe',
          extraInfo: ['Phone: 123-456-7890', 'Address: 123 Example St, Example City']
        }
      ],
      technicalSkills: [
        {
          id: 1,
          languages: 'JavaScript, TypeScript, Python',
          frameworks: 'Angular, React, Node.js',
          devtools: 'Git, Docker, Jenkins'
        }
      ],
      experiences: [
        {
          id: 1,
          title: 'Software Engineer',
          company: 'Tech Company',
          location: 'Example City',
          dateRange: '2020 - Present',
          bulletpoints: ['Developed web applications using Angular', 'Collaborated with cross-functional teams']
        },
        {
          id: 2,
          title: 'Junior Developer',
          company: 'Startup Inc.',
          location: 'Example City',
          dateRange: '2019 - 2020',
          bulletpoints: ['Assisted in developing mobile applications', 'Maintained codebase and fixed bugs']
        },
        {
          id: 3,
          title: 'Intern',
          company: 'Software Solutions',
          location: 'Example City',
          dateRange: '2018 - 2019',
          bulletpoints: ['Worked on internal tools', 'Participated in code reviews']
        }
      ],
      leadershipExps: [
        {
          id: 1,
          position: 'Team Lead',
          company: 'Tech Company',
          dateRange: '2021 - Present',
          location: 'Example City',
          bulletpoints: ['Led a team of 5 developers', 'Managed project timelines and deliverables']
        },
        {
          id: 2,
          position: 'Project Manager',
          company: 'Startup Inc.',
          dateRange: '2020 - 2021',
          location: 'Example City',
          bulletpoints: ['Coordinated with stakeholders', 'Oversaw project development']
        },
        {
          id: 3,
          position: 'Club President',
          company: 'University of Example',
          dateRange: '2017 - 2018',
          location: 'Example City',
          bulletpoints: ['Organized events', 'Led club meetings']
        }
      ],
      projects: [
        {
          id: 1,
          name: 'Project A',
          techs: 'Angular, Node.js',
          bulletpoints: ['Developed a web application', 'Implemented RESTful APIs']
        },
        {
          id: 2,
          name: 'Project B',
          techs: 'React, Express',
          bulletpoints: ['Built a single-page application', 'Integrated with third-party APIs']
        },
        {
          id: 3,
          name: 'Project C',
          techs: 'Vue, Firebase',
          bulletpoints: ['Created a real-time chat application', 'Deployed on Firebase']
        }
      ]
    }];
    return resumeSection
  }

  constructor() { }
}
