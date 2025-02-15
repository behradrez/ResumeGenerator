package com.example.ResumeGeneratorBackend.dto;

import java.util.List;
import java.util.ArrayList;

import com.example.ResumeGeneratorBackend.model.ResumeObject;
import com.example.ResumeGeneratorBackend.model.SectionObjects.*;



public class ResumeSectionsDTO {
    private List<LeadershipObject> leadershipExps = new ArrayList<>();
    private List<EducationObject> educationExps = new ArrayList<>();
    private List<HeaderObject> header = new ArrayList<>();
    private List<ExperienceObject> experiences = new ArrayList<>();
    private List<TechnicalObject> technicalSkills = new ArrayList<>();
    private List<ProjectObject> projects = new ArrayList<>();


    public ResumeSectionsDTO(){}

    public ResumeSectionsDTO(List<LeadershipObject> leadershipExps, List<EducationObject> educationExps, List<HeaderObject> header, List<ExperienceObject> experienceExps, List<TechnicalObject> technicalSkills, List<ProjectObject> projects) {
        this.leadershipExps = leadershipExps;
        this.educationExps = educationExps;
        this.header = header;
        this.experiences = experienceExps;
        this.technicalSkills = technicalSkills;
        this.projects = projects;
        }

    public void setLeadershipExps(List<LeadershipObject> leadershipExps) {
        this.leadershipExps = (leadershipExps != null) ? leadershipExps : new ArrayList<>();
    }

    public void setEducationExps(List<EducationObject> educationExps) {
        this.educationExps = (educationExps != null) ? educationExps : new ArrayList<>();
    }

    public void setHeader(List<HeaderObject> header) {
        this.header = (header != null) ? header : new ArrayList<>();
    }

    public void setExperiences(List<ExperienceObject> experiences) {
        this.experiences = (experiences != null) ? experiences : new ArrayList<>();
    }

    public void setTechnicalSkills(List<TechnicalObject> technicalSkills) {
        this.technicalSkills = (technicalSkills != null) ? technicalSkills : new ArrayList<>();
    }

    public void setProjects(List<ProjectObject> projects) {
        this.projects = (projects != null) ? projects : new ArrayList<>();
    }

    public List<LeadershipObject> getLeadershipExps() {
        return leadershipExps;
    }

    public List<EducationObject> getEducationExps() {
        return educationExps;
    }

    public List<HeaderObject> getHeader() {
        return header;
    }

    public List<ExperienceObject> getExperiences() {
        return experiences;
    }

    public List<TechnicalObject> getTechnicalSkills() {
        return technicalSkills;
    }

    public List<ProjectObject> getProjects() {
        return projects;
    }

    public ResumeObject convertToResume(){
        ResumeObject resume = new ResumeObject();

        resume.addObjects(new ArrayList<>(leadershipExps));
        resume.addObjects(new ArrayList<>(educationExps));
        resume.addObjects(new ArrayList<>(header));
        resume.addObjects(new ArrayList<>(experiences));
        resume.addObjects(new ArrayList<>(technicalSkills));
        resume.addObjects(new ArrayList<>(projects));
    
        return resume;
    }


}
