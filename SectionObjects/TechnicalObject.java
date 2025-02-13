package SectionObjects;

public class TechnicalObject extends SectionObject {
    private String latexString;
    
    private String languages;
    private String frameworks;
    private String devtools;


    public TechnicalObject(){
        super("Technical", Section.TECH_SKILLS);
    }

    public TechnicalObject(String languages, String frameworks, String devtools){
        super("Tech", Section.TECH_SKILLS);

        this.languages = languages;
        this.frameworks = frameworks;
        this.devtools = devtools;

        generateLatexFormattedString();

    }

    private void generateLatexFormattedString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\section{Technical Skills}\n");
        sb.append("\\begin{itemize}[leftmargin=0.15in, label={}]\n\\small{\\item{\n\\textbf{Languages: }");
        LatexFormatter.addInBrackets(sb, languages);
        sb.append("\\\\\n\\textbf{Frameworks: }");
        LatexFormatter.addInBrackets(sb, frameworks);
        sb.append("\\\\\n\\textbf{Developer Tools: }");
        LatexFormatter.addInBrackets(sb, devtools);
        sb.append("\n}}\n\\end{itemize}");

        latexString = sb.toString();
    }

    @Override
    public String getLatexString() {
        return latexString;
    }
}
