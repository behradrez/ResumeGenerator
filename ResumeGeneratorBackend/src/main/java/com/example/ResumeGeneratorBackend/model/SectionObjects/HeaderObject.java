package com.example.ResumeGeneratorBackend.model.SectionObjects;

import java.util.List;

import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class HeaderObject extends SectionObject {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private UserAccount user;

    private List<String> extraInfo;
    private String linkedInLink;
    private String githubLink;
    private String email;
    private String name;

    public void setExtraInfo(List<String> extraInfo) {
        this.extraInfo = extraInfo;
    }
    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }
    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUser(UserAccount user){
        this.user = user;
    }


    public List<String> getExtraInfo() {
        return extraInfo;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public HeaderObject(){
        super(Section.HEADER);
    }

    public HeaderObject(String name, List<String> extraInfo,String email, String linkedInLink, String githubLink){
        super(Section.HEADER);
        this.email = email;
        this.name = name;
        this.extraInfo = extraInfo;
        this.linkedInLink = linkedInLink;
        this.githubLink = githubLink;
    }

    public String generateLatexString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\\begin{center}\n\\textbf{\\Huge \\scshape ").append(name).append("}\\\\\n");
        sb.append("\\vspace{1pt}\n\\small ");
        for (String info : extraInfo) {
            sb.append(info).append(" $|$ ");
        }
        sb.append("\\href{mailto"+email+"}{\\underline");
        LatexFormatter.addInBrackets(sb, email);
        sb.append("} $|$ ");

        sb.append("\\href");
        LatexFormatter.addInBrackets(sb, linkedInLink);
        sb.append("{\\underline{LinkedIn}} $|$ \\href");
        LatexFormatter.addInBrackets(sb, githubLink);
        sb.append("{\\underline{GitHub}}\n\\end{center}");

        String latexString = sb.toString();
        return latexString;
    }
}
