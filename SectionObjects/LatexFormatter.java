package SectionObjects;

public class LatexFormatter {
    public static String generateResumeBulletPoints(String[] bulletpoints){
        StringBuilder resumeItem = new StringBuilder();
        resumeItem.append("\\resumeItemListStart\n");
        for(String bulletpoint: bulletpoints){
            resumeItem.append("\t\\resumeItem");
            addInBrackets(resumeItem, bulletpoint);
            resumeItem.append("\n");
        }
        resumeItem.append("\\resumeItemListEnd");
        return resumeItem.toString();
    }

    public static void addInBrackets(StringBuilder sb,String word){
        sb.append('{').append(word.replace("%","\\%")).append('}');
    } 

    public static void addInBrackets(StringBuilder sb, String[] words){
        for(String word: words){
            addInBrackets(sb, word);
        }
    }

    public static String boldenWords(String input, String[] wordsToBolden){
        String copy = input;
        for(String word: wordsToBolden){
            copy = copy.replace(word, "\\textbf{"+word+"}");
        }
        return copy;
    }


    public static String createSection(String sectionTitle, String content){
        StringBuilder myString = new StringBuilder();
        myString.append("\\section{");
        myString.append(sectionTitle);
        myString.append("}\n");
        myString.append("\t");
        myString.append(content);

        return myString.toString();
    }

    public static void main(String[] args){
        String bulletpoints[] = {"First let me hop", "Out the mf porsche","Ive never been this high"};
        String myBulletList = generateResumeBulletPoints(bulletpoints);
        myBulletList = boldenWords(myBulletList, new String[]{"First", "mf"});
        System.out.println(myBulletList);

    }
}
