package com.example.ResumeGeneratorBackend.model.SectionObjects;

import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TechnicalObject extends SectionObject {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private UserAccount user;
    
    
    private String languages;
    private String frameworks;
    private String devtools;


    public TechnicalObject(){
        super(Section.TECH_SKILLS);
    }
    public void setUser(UserAccount user){
        this.user = user;
    }
    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setFrameworks(String frameworks) {
        this.frameworks = frameworks;
    }

    public void setDevtools(String devtools) {
        this.devtools = devtools;
    }
    public int getId() {
        return id;
    }

    public String getLanguages() {
        return languages;
    }

    public String getFrameworks() {
        return frameworks;
    }

    public String getDevtools() {
        return devtools;
    }

    public String generateLatexString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\section{Technical Skills}\n");
        sb.append("\\begin{itemize}[leftmargin=0.15in, label={}]\n\\small{\\item{\n\\textbf{Languages: }");
        LatexFormatter.addInBrackets(sb, languages);
        sb.append("\\\\\n\\textbf{Frameworks: }");
        LatexFormatter.addInBrackets(sb, frameworks);
        sb.append("\\\\\n\\textbf{Developer Tools: }");
        LatexFormatter.addInBrackets(sb, devtools);
        sb.append("\n}}\n\\end{itemize}");

        return sb.toString();
    }
}
