package com.example.ResumeGeneratorBackend.model.SectionObjects;

import java.util.List;

import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LeadershipObject extends SectionObject {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private UserAccount user;

    private String position;
    private String location;
    private String company;
    private String dateRange;
    private List<String> bulletpoints;

    public LeadershipObject(){
        super(Section.LEADERSHIP);
    }

    public void setUser(UserAccount user){
        this.user = user;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public void setBulletpoints(List<String> bulletpoints) {
        this.bulletpoints = bulletpoints;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getDateRange() {
        return dateRange;
    }

    public List<String> getBulletpoints() {
        return bulletpoints;
    }


    public String generateLatexString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\\resumeSubheading");
        LatexFormatter.addInBrackets(sb, new String[]{position, location, company, dateRange});
        sb.append("\\resumeItemListStart\n");
        for (String bullet : bulletpoints) {
            sb.append("\\resumeItem");
            LatexFormatter.addInBrackets(sb, bullet);
            sb.append("\n");
        }
        sb.append("\\resumeItemListEnd\n");

        String latexString = sb.toString();
        return latexString;
    }
}
