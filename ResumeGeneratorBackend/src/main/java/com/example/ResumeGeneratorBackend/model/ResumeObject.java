package com.example.ResumeGeneratorBackend.model;

import java.util.ArrayList;
import java.util.List;
import com.example.ResumeGeneratorBackend.model.SectionObjects.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ResumeObject {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private UserAccount user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ExperienceObject> experiences = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProjectObject> projects = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<LeadershipObject> leadershipExps = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    private TechnicalObject technicalObj;
    @OneToOne(cascade = CascadeType.ALL)
    private EducationObject educationObj;
    @OneToOne(cascade =  CascadeType.ALL)
    private HeaderObject headerObj;


    public ResumeObject(){}

    public void setUser(UserAccount user){
        this.user = user;
    }
    public void setExperiences(List<ExperienceObject> experiences) {
        this.experiences = experiences;
    }

    public void setProjects(List<ProjectObject> projects) {
        this.projects = projects;
    }

    public void setLeadershipExps(List<LeadershipObject> leadershipExps) {
        this.leadershipExps = leadershipExps;
    }

    public void setTechnicalObj(TechnicalObject technicalObj) {
        this.technicalObj = technicalObj;
    }

    public void setEducationObj(EducationObject educationObj) {
        this.educationObj = educationObj;
    }

    public void setHeaderObj(HeaderObject headerObj) {
        this.headerObj = headerObj;
    }

    

    public void addObject(SectionObject obj){
        switch (obj.getType()) {
            case EXPERIENCE:
                experiences.add((ExperienceObject)obj);
                break;
            case PROJECTS:
                projects.add((ProjectObject)obj);
                break;
            case LEADERSHIP:
                leadershipExps.add((LeadershipObject)obj);
                break;
            case TECH_SKILLS:
                technicalObj = (TechnicalObject)obj;
                break;
            case EDUCATION:
                educationObj = (EducationObject)obj;
                break;
            case HEADER:
                headerObj = (HeaderObject) obj;
                break;
            default:
                break;
        }
    }

    public void addObjects(List<SectionObject> objs){
        for (SectionObject sectionObject : objs) {
            this.addObject(sectionObject);
        }
    }

    public String getLatexString(){
        StringBuilder resumeString = new StringBuilder();
        String doc = "\\documentclass[letterpaper,11pt]{article}\n" + //
        "\n" + //
        "\\usepackage{latexsym}\n" + //
        "\\usepackage[empty]{fullpage}\n" + //
        "\\usepackage{titlesec}\n" + //
        "\\usepackage{marvosym}\n" + //
        "\\usepackage[usenames,dvipsnames]{color}\n" + //
        "\\usepackage{verbatim}\n" + //
        "\\usepackage{enumitem}\n" + //
        "\\usepackage[hidelinks]{hyperref}\n" + //
        "\\usepackage{fancyhdr}\n" + //
        "\\usepackage[english]{babel}\n" + //
        "\\usepackage{tabularx}\n" + //
        "\\usepackage{graphicx}\n" + //
        "\\input{glyphtounicode}\n" + //
        "\n" + //
        "\n" + //
        "\\pagestyle{fancy}\n" + //
        "\\fancyhf{} % clear all header and footer fields\n" + //
        "\\fancyfoot{}\n" + //
        "\\renewcommand{\\headrulewidth}{0pt}\n" + //
        "\\renewcommand{\\footrulewidth}{0pt}\n" + //
        "\n" + //
        "\\addtolength{\\oddsidemargin}{-0.5in}\n" + //
        "\\addtolength{\\evensidemargin}{-0.5in}\n" + //
        "\\addtolength{\\textwidth}{1in}\n" + //
        "\\addtolength{\\topmargin}{-.5in}\n" + //
        "\\addtolength{\\textheight}{1.0in}\n" + //
        "\n" + //
        "\\urlstyle{same}\n" + //
        "\n" + //
        "\\raggedbottom\n" + //
        "\\raggedright\n" + //
        "\\setlength{\\tabcolsep}{0in}\n" + //
        "\n" + //
        "\\titleformat{\\section}{\n" + //
        "  \\vspace{-8pt}\\scshape\\raggedright\\large\n" + //
        "}{}{0em}{}[\\color{black}\\titlerule \\vspace{-6pt}]\n" + //
        "\n" + //
        "\\pdfgentounicode=1\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeItem}[1]{\n" + //
        "  \\item\\small{\n" + //
        "    {#1 \\vspace{-2pt}}\n" + //
        "  }\n" + //
        "}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubheading}[4]{\n" + //
        "  \\vspace{-2pt}\\item\n" + //
        "    \\begin{tabular*}{0.97\\textwidth}[t]{l@{\\extracolsep{\\fill}}r}\n" + //
        "      \\textbf{#1} & #2 \\\\\n" + //
        "      \\textit{\\small#3} & \\textit{\\small #4} \\\\\n" + //
        "    \\end{tabular*}\\vspace{-7pt}\n" + //
        "}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubheadingTwo}[5]{\n" + //
        "  \\vspace{-2pt}\\item\n" + //
        "    \\begin{tabular*}{0.97\\textwidth}[t]{l@{\\extracolsep{\\fill}}r}\n" + //
        "      \\textbf{#1} & #2 \\\\\n" + //
        "      \\textit{\\small#3} & \\textit{\\small #4} \\\\\n" + //
        "      \\text{\\small#5} \\\\\n" + //
        "    \\end{tabular*}\\vspace{-7pt}\n" + //
        "}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubheadingThree}[6]{\n" + //
        "  \\vspace{-2pt}\\item\n" + //
        "    \\begin{tabular*}{0.97\\textwidth}[t]{l@{\\extracolsep{\\fill}}r}\n" + //
        "      \\textbf{#1} & #2 \\\\\n" + //
        "      \\textit{\\small#3} & \\textit{\\small #4} \\\\\n" + //
        "      \\text{\\small#5} \\\\\n" + //
        "    \\end{tabular*}\n" + //
        "    \\text{\\small#6}\n" + //
        "    \\vspace{-7pt}\n" + //
        "}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubSubheading}[2]{\n" + //
        "    \\item\n" + //
        "    \\begin{tabular*}{0.97\\textwidth}{l@{\\extracolsep{\\fill}}r}\n" + //
        "      \\textit{\\small#1} & \\textit{\\small #2} \\\\\n" + //
        "    \\end{tabular*}\\vspace{-7pt}\n" + //
        "}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeProjectHeading}[2]{\n" + //
        "    \\item\n" + //
        "    \\begin{tabular*}{0.97\\textwidth}{l@{\\extracolsep{\\fill}}r}\n" + //
        "      \\small#1 & #2 \\\\\n" + //
        "    \\end{tabular*}\\vspace{-7pt}\n" + //
        "}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubItem}[1]{\\resumeItem{#1}\\vspace{-4pt}}\n" + //
        "\n" + //
        "\\renewcommand\\labelitemii{$\\vcenter{\\hbox{\\tiny$\\bullet$}}$}\n" + //
        "\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubHeadingListStart}{\\begin{itemize}[leftmargin=0.15in, label={}]}\n" + //
        "\\n" + //
        "ewcommand{\\resumeSubHeadingListEnd}{\\end{itemize}}\n" + //
        "\\n" + //
        "ewcommand{\\resumeItemListStart}{\\begin{itemize}}\n" + //
        "\\n" + //
        "ewcommand{\\resumeItemListEnd}{\\end{itemize}\\vspace{-5pt}}\n" + //
        "\n" + //
        "%-------------------------------------------\n" + //
        "%%%%%%  RESUME STARTS HERE  %%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" + //
        "\n" + //
        "\n" + //
        "\\begin{document}\n";

        resumeString.append(doc);

        resumeString.append("\n\n%----HEADER----\n");
        resumeString.append(headerObj.generateLatexString());
        resumeString.append("\n\n%----EDUCATION----\n");
        resumeString.append(educationObj.generateLatexString());
        resumeString.append("\n\n%----TECHNICAL SKILLS----\n");
        resumeString.append(technicalObj.generateLatexString());

        resumeString.append("\n\n%----EXPERIENCE----\n\\section{Experience}\n\\resumeSubHeadingListStart\n");
        for(ExperienceObject exp: experiences){
            resumeString.append(exp.generateLatexString());
        }
        resumeString.append("\n\\resumeSubHeadingListEnd\n");

        resumeString.append("\n\n%----LEADERSHIP----\n\\section{Leadership}\n\\resumeSubHeadingListStart\n");
        for(LeadershipObject exp: leadershipExps){
            resumeString.append(exp.generateLatexString());
        }
        resumeString.append("\n\\resumeSubHeadingListEnd\n");

        resumeString.append("\n\n%----PROJECTS----\n\\section{Projects}\n\\resumeSubHeadingListStart\n");
        for(ProjectObject proj: projects){
            resumeString.append(proj.generateLatexString());
        }
        resumeString.append("\n\\resumeSubHeadingListEnd\n");
        resumeString.append("\n\\end{document}\n");

        return resumeString.toString();
    }

}
