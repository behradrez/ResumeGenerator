package SectionObjects;


public class ExperienceObject extends SectionObject {
    private String jobTitle;
    private String companyName;
    private String dateRange;
    private String location;
    private String[] bulletpoints;

    private String latexFormattedText;

    public ExperienceObject(){
        super("", Section.EXPERIENCE);
    }

    public ExperienceObject(String jobTitle, String companyName, String dateRange, String location, String[] bulletpoints){
        super(jobTitle, Section.EXPERIENCE);

        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.dateRange = dateRange;
        this.location = location;
        this.bulletpoints = bulletpoints;

        generateLatexFormattedString();
    }

    private void generateLatexFormattedString(){
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

        latexFormattedText = singleExperience.toString();
    }

    @Override
    public String getLatexString(){
        return latexFormattedText;
    }


    public static void main(String[] args) {
        String[] bullets = {"These are my bullet points","This is another one","And one last one"};
        ExperienceObject exp = new ExperienceObject("SWE", "some comp","August 2020-August 2024", "San Francisco", bullets);
        System.out.println(exp.getLatexString());
    }
}
