package SectionObjects;

public class ProjectObject extends SectionObject {
    private String latexString;

    private String name;
    private String techs;
    private String[] bulletpoints;

    public ProjectObject(){
        super("Project", Section.PROJECTS);
    }

    public ProjectObject(String name, String techs, String[] bullets){
        super("Project", Section.PROJECTS);
        
        this.name = name;
        this.techs = techs;
        this.bulletpoints = bullets;

        generateLatexFormattedString();
    }

    private void generateLatexFormattedString(){
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

        latexString = sb.toString();
    }

    @Override
    public String getLatexString() {
        return latexString;
    }
}
