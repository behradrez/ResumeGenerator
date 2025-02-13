package SectionObjects;
public abstract class SectionObject {
    private Section type;
    private String text;

    public SectionObject(String text, Section type){
        this.text = text;
        this.type = type;
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    public Section getType(){
        return this.type;
    }

    public abstract String getLatexString();
    
}
