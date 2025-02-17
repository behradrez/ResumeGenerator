package com.example.ResumeGeneratorBackend.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private String username;
    private String hashedPass;
    private byte[] salt;

    public UserAccount(){}

    public UserAccount(String username){
        this.username = username;
    }



    public void setHashedPass(String hashedPass){
        this.hashedPass = hashedPass;
    }

    public String getHashedPass(){
        return hashedPass;
    }
 
    public void setSalt(byte[] newSalt){
        salt = newSalt;
    }
    public byte[] getSalt(){
        return salt;
    }


    @PrePersist
    protected void onCreate(){
        creationDate = new Date();
    }
    
    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public Date getCreationDate(){
        return creationDate;
    }
}
