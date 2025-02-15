package com.example.ResumeGeneratorBackend.model.SectionObjects;

import java.util.List;

import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExperienceObject extends SectionObject {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private UserAccount user;
    
    private String jobTitle;
    private String companyName;
    private String dateRange;
    private String location;
    private List<String> bulletpoints;

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBulletpoints(List<String> bulletpoints) {
        this.bulletpoints = bulletpoints;
    }

    public void setUser(UserAccount user){
        this.user = user;
    }
    
    public int getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getBulletpoints() {
        return bulletpoints;
    }

    public ExperienceObject(){
        super(Section.EXPERIENCE);
    }
    
    public ExperienceObject(String jobTitle, String companyName, String dateRange, String location, List<String> bulletpoints){
        super(Section.EXPERIENCE);

        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.dateRange = dateRange;
        this.location = location;
        this.bulletpoints = bulletpoints;
    }
    
    @Override
    public String generateLatexString(){
        StringBuilder singleExperience = new StringBuilder();
        singleExperience.append("\\resumeSubheading{");
        singleExperience.append(jobTitle);
        singleExperience.append("}{");
        singleExperience.append(location);
        singleExperience.append("}{");
        singleExperience.append(companyName);
        singleExperience.append("}{");
        singleExperience.append(dateRange);
        singleExperience.append("}\n");
        singleExperience.append(LatexFormatter.generateResumeBulletPoints(bulletpoints));

        return singleExperience.toString();
    }
}
