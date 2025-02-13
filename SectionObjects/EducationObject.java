package SectionObjects;

public class EducationObject extends SectionObject {
    private String latexString;
    
    public EducationObject(String schoolName, String location, String degree, String dateRange, String gpa, String courses){
        super("Education", Section.EDUCATION);
        StringBuilder sb = new StringBuilder();
        sb.append("\\section{Education}\n\\resumeSubHeadingListStart\n\\resumeSubheadingThree\n");
        LatexFormatter.addInBrackets(sb, new String[]{schoolName, location, degree, dateRange, gpa, courses});
        sb.append("\n\\resumeSubHeadingListEnd\n");
        latexString = sb.toString();
    }

    @Override
    public String getLatexString() {
        return latexString;
    }

}
