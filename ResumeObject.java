import java.util.ArrayList;

import SectionObjects.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;
import java.io.File;

public class ResumeObject {
    private ArrayList<ExperienceObject> experiences;
    private ArrayList<ProjectObject> projects;
    private ArrayList<LeadershipObject> leadershipExps;
    
    private TechnicalObject technicalObj;
    private EducationObject educationObj;
    private HeaderObject headerObj;


    private String finalLatexString;

    public ResumeObject(){
        experiences = new ArrayList<ExperienceObject>();
        projects = new ArrayList<ProjectObject>();
        leadershipExps = new ArrayList<LeadershipObject>();
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

    public void addObjects(SectionObject[] objs){
        for (SectionObject sectionObject : objs) {
            this.addObject(sectionObject);
        }
    }

    private void updateLatexString(){
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
        resumeString.append(headerObj.getLatexString());
        resumeString.append("\n\n%----EDUCATION----\n");
        resumeString.append(educationObj.getLatexString());
        resumeString.append("\n\n%----TECHNICAL SKILLS----\n");
        resumeString.append(technicalObj.getLatexString());

        resumeString.append("\n\n%----EXPERIENCE----\n\\section{Experience}\n\\resumeSubHeadingListStart\n");
        for(ExperienceObject exp: experiences){
            resumeString.append(exp.getLatexString());
        }
        resumeString.append("\n\\resumeSubHeadingListEnd\n");

        resumeString.append("\n\n%----LEADERSHIP----\n\\section{Leadership}\n\\resumeSubHeadingListStart\n");
        for(LeadershipObject exp: leadershipExps){
            resumeString.append(exp.getLatexString());
        }
        resumeString.append("\n\\resumeSubHeadingListEnd\n");

        resumeString.append("\n\n%----PROJECTS----\n\\section{Projects}\n\\resumeSubHeadingListStart\n");
        for(ProjectObject proj: projects){
            resumeString.append(proj.getLatexString());
        }
        resumeString.append("\n\\resumeSubHeadingListEnd\n");
        resumeString.append("\n\\end{document}\n");

        finalLatexString = resumeString.toString();
    }
    
    public String getLatexResume(){
        updateLatexString();
        return finalLatexString;
    }

    public static ResumeObject createFromJSON(){
        JSONObject jo;
        ResumeObject resume = new ResumeObject();
        try{
            String content = new String(Files.readAllBytes(Paths.get("Resume.json")));
            jo = new JSONObject(content);            
        }catch(IOException e){
            return resume;
        }

        JSONObject headerJson = jo.getJSONObject("Header");
        HeaderObject header = new HeaderObject(
            headerJson.getString("Name"),
            headerJson.getJSONArray("Extras").toList().toArray(new String[0]),
            headerJson.getString("Email"),
            headerJson.getString("Linkedin"),
            headerJson.getString("Github")
        );
        resume.addObject(header);

        JSONObject educationJson = jo.getJSONObject("Education");
        EducationObject education = new EducationObject(
            educationJson.getString("Institution"),
            educationJson.getString("Location"),
            educationJson.getString("Degree"),
            educationJson.getString("DateRange"),
            educationJson.getString("Grade"),
            educationJson.getString("RelevantCourses")
        );
        resume.addObject(education);

        JSONObject technicalJson = jo.getJSONObject("TechnicalSkills");
        TechnicalObject technical = new TechnicalObject(
            technicalJson.getString("Languages"),
            technicalJson.getString("Frameworks"),
            technicalJson.getString("DeveloperTools")
        );
        resume.addObject(technical);

        JSONArray experiencesJson = jo.getJSONArray("Experiences");
        for (int i = 0; i < experiencesJson.length(); i++) {
            JSONObject expJson = experiencesJson.getJSONObject(i);
            ExperienceObject experience = new ExperienceObject(
                expJson.getString("JobTitle"),
                expJson.getString("Company"),
                expJson.getString("DateRange"),
                expJson.getString("Location"),
                expJson.getJSONArray("Bulletpoints").toList().toArray(new String[0])
            );
            resume.addObject(experience);
        }

        JSONArray leadershipJson = jo.getJSONArray("Leadership");
        for (int i = 0; i < leadershipJson.length(); i++) {
            JSONObject leadJson = leadershipJson.getJSONObject(i);
            LeadershipObject leadership = new LeadershipObject(
                leadJson.getString("Position"),
                leadJson.getString("Location"),
                leadJson.getString("Company"),
                leadJson.getString("DateRange"),
                leadJson.getJSONArray("Bulletpoints").toList().toArray(new String[0])
            );
            resume.addObject(leadership);
        }
        
        JSONArray projectsJson = jo.getJSONArray("Projects");
        for (int i = 0; i < projectsJson.length(); i++) {
            JSONObject projJson = projectsJson.getJSONObject(i);
            ProjectObject project = new ProjectObject(
                projJson.getString("Name"),
                projJson.getString("TechnologiesUsed"),
                projJson.getJSONArray("Bulletpoints").toList().toArray(new String[0])
            );
            resume.addObject(project);
        }

        return resume;
    }

    public static void main(String[] args){
        HeaderObject header = new HeaderObject("Behrad Rezaie", new String[]{"US Citizen", "fsef"}, "behradrezaie07@gmail.com", "linkedin.com", "github.com");
        EducationObject ed = new EducationObject("McGill University", "Montreal, QC", "Bachelor of Computer Engineering, Minor in Applied AI", "August 2022 -- April 2026", "GPA: 3.90/4.00", "A cople courses ehre and there");
        TechnicalObject technicalObject = new TechnicalObject("Java, Python, Javascript","React, Spring Boot, Next.js", "Git, Docker, AWS");
        ExperienceObject exp1 = new ExperienceObject("Software Engineer Intern", "Koasec Cybersecurity", "2023-2024", "Montreal, QC", new String[]{"Developed a frontend with \\textbf{React Next.js, TailwindCSS}, and NextUI, enhancing scalability \\& user experience","Optimized heavy operations by implementing multi-threading, reducing response times from \\textbf{30s to 10s}","Built a \\textbf{Flutter} extension for the app, integrating \\textbf{OAuth \\& Firebase} for secure real-time team notifications","Developed backend services and APIs to automate multi-step workflows, reducing manual tasks by \\textbf{40\\%}"});
        ExperienceObject exp2 = new ExperienceObject("Software Engineer Intern", "Koasec Cybersecurity", "2024-2025", "Montreal, QC", new String[]{"Did a lot of stuff I promise","i entirely forgot to list them here tho","Also did a third thing cant forget that obviously"});
        LeadershipObject leadexp = new LeadershipObject("Teaching Assistant", "Montreal QC", "McGill University", "2024-2025", new String[]{"Reinforced student learning through weekly office hours and tutorials","Assisted 20+ students with approaching assignments and whatnot"});
        ProjectObject p1 = new ProjectObject("McGill Exams Tracker", "Typescript, React Next.js, Supabase", new String[]{"Developed a live tracking website for 750 exams, servicing over 1000 students per semester","Developed full-stack services using Next.js with Supabase and Prisma integrations"});
        ProjectObject p2 = new ProjectObject("Sports Center App", "Java, Vue, Spring Boot, PostgreSQL", new String[]{"Led a team of 6 in designing and creating full stack CRUD services","Optimized data management with Postgres and JPA tags for improved relationship mapping","Implemented a frontend interface using Vue, Javascript, and CSS to ensure a reliable and intuitive experience"});
        ProjectObject p3 = new ProjectObject("TerraGUI","Golang, React Next.js", new String[]{"Created a full-stack app to allows users to visually design and organize cloud infrastructure before deployment","Developed backend API in Golang, handling file generation and deployment artifact creation","Designed a dynamic front-end experience with React Next.js and MaterialUI, providing drag-and-drop\n" + //
                        "infrastructure design and real-time deployment validation"});
        ResumeObject resume = new ResumeObject();

        //resume.addObjects(new SectionObject[]{header, ed, technicalObject, exp1, exp2, leadexp, p1, p2,p3});

        //System.out.println(resume.getLatexResume());

        resume = ResumeObject.createFromJSON();
        System.out.println(resume.getLatexResume());
    }

}
