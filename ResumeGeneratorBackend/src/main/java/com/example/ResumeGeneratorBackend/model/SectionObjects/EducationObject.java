package com.example.ResumeGeneratorBackend.model.SectionObjects;

import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;


@Entity
public class EducationObject extends SectionObject {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private UserAccount user;

    private String schoolName;
    private String location;
    private String degree;
    private String dateRange;
    private String gpa;
    private String courses;

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getLocation() {
        return location;
    }

    public String getDegree() {
        return degree;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getGpa() {
        return gpa;
    }

    public String getCourses() {
        return courses;
    }

    public EducationObject(){
        super(Section.EDUCATION);
    }

    public void setUser(UserAccount user){
        this.user = user;
    }

    public String generateLatexString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\section{Education}\n\resumeSubHeadingListStart\n\resumeSubheadingThree\n");
        LatexFormatter.addInBrackets(sb, new String[]{schoolName, location, degree, dateRange, gpa, courses});
        sb.append("\n\resumeSubHeadingListEnd\n");
        return sb.toString();
    }


}
