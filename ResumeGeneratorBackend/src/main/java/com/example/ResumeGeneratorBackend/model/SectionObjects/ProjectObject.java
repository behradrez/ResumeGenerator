package com.example.ResumeGeneratorBackend.model.SectionObjects;

import java.util.List;

import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjectObject extends SectionObject {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private UserAccount user;
    
    private String name;
    private String techs;
    private List<String> bulletpoints;

    public ProjectObject(){
        super(Section.PROJECTS);
    }

    public void setUser(UserAccount user){
        this.user = user;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTechs(String techs){
        this.techs = techs;
    }
    public void setBulletpoints(List<String> bulletpoints){
        this.bulletpoints = bulletpoints;
    }

    public int getId() {
        return id;
    }

    public UserAccount getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getTechs() {
        return techs;
    }

    public List<String> getBulletpoints() {
        return bulletpoints;
    }
    
    public String generateLatexString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\resumeProjectHeading\n{\\textbf");
        LatexFormatter.addInBrackets(sb, name);
        sb.append(" $|$ \\emph");
        LatexFormatter.addInBrackets(sb, techs);
        sb.append("}");
        LatexFormatter.addInBrackets(sb, "");
        sb.append("\n\\resumeItemListStart");
        for (String bullet : bulletpoints) {
            sb.append("\n\\resumeItem");
            LatexFormatter.addInBrackets(sb, bullet);
            sb.append("\n");
        }
        sb.append("\\resumeItemListEnd\n");

        return sb.toString();
    }
}
