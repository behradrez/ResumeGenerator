package SectionObjects;

public class LeadershipObject extends SectionObject {
    private String latexString;

    private String position;
    private String location;
    private String company;
    private String dateRange;
    private String[] bulletpoints;

    public LeadershipObject(){
        super("Leadership", Section.LEADERSHIP);
    }

    public LeadershipObject(String position, String location, String company, String dateRange, String[] bullets){
        super("Leadership", Section.LEADERSHIP);
        this.position = position;
        this.location = location;
        this.company = company;
        this.dateRange = dateRange;
        this.bulletpoints = bullets;

        generateLatexFormattedString();
    }

    private void generateLatexFormattedString(){
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

        latexString = sb.toString();
    }

    @Override
    public String getLatexString() {
        return latexString;
    }
}
