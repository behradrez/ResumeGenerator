export interface ResumeSectionDTO {
    id?: number;
    leadershipExps?: LeadershipObject[]
    educationExps?: EducationObject[]
    header?: HeaderObject[]
    experiences?: ExperienceObject[]
    technicalSkills?: TechnicalObject[]
    projects?: ProjectObject[]
}

export interface LeadershipObject {
    id?:number;
    position: string;
    company: string;
    dateRange: string;
    location: string;
    bulletpoints: string[];
}

export interface EducationObject {
    id?:number;
    degree: string;
    schoolName: string;
    dateRange: string;
    gpa: string;
    location:string;
    courses:string;
}

export interface HeaderObject {
    id?: number;
    name: string;
    email: string;
    linkedInLink: string;
    githubLink: string;
    extraInfo: string[];
}

export interface ExperienceObject {
    id?: number; 
    title: string;
    company: string;
    location:string;
    dateRange: string;
    bulletpoints: string[];
}

export interface TechnicalObject {
    id?: number;
    languages: string;
    frameworks: string;
    devtools: string;
}

export interface ProjectObject {
    id: number;
    name: string;
    techs:string;
    bulletpoints: string[];
}

export interface SectionObject {
    id: number;
    type: Section;
}

export enum Section {
    EDUCATION,
    EXPERIENCE,
    HEADER,
    LEADERSHIP,
    PROJECTS,
    TECH_SKILLS
}

export interface UserAccount {
    id: number;
    username: string;
    password: string;
    email: string;
}