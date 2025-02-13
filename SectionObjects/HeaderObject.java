package SectionObjects;

public class HeaderObject extends SectionObject {
    private String latexString;

    public HeaderObject(String name, String[] extraInfo,String email, String linkedInLink, String githubLink ){
        super("Header", Section.HEADER);

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

        latexString = sb.toString();
    }

    @Override
    public String getLatexString() {
        return latexString;
    }
}
