package com.example.ResumeGeneratorBackend.model.SectionObjects;

public abstract class SectionObject {
    private Section type;

    public SectionObject(Section type){
        this.type = type;
    }

    public Section getType(){
        return this.type;
    }

    public abstract String generateLatexString();
    
}
