package com.example.ResumeGeneratorBackend.dto;

public class UserAccountDTO{
    private String username;
    private String pass;

    public UserAccountDTO() {}


    public String getUsername(){
        return username;
    }
    public String getPass(){
        return pass;
    }

    public void setUsername(String user){
        username = user;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
}