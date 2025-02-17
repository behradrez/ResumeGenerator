package com.example.ResumeGeneratorBackend.service;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResumeGeneratorBackend.dao.UserRepository;
import com.example.ResumeGeneratorBackend.model.UserAccount;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUserAccount(String username, String pass) throws Exception{
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("User already exists by that username");
        }
        UserAccount user = new UserAccount(username);
       
        // Hash and save password
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String hashedPass = Base64.getEncoder().encodeToString(hash);


        user.setSalt(salt);
        user.setHashedPass(hashedPass);
        userRepository.save(user);
    }

    @Transactional
    public int attemptLogin(String username, String password) throws Exception{
        if(!userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("No user by given username");
        }
        UserAccount user = userRepository.findByUsername(username);
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), user.getSalt(), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String newHash = Base64.getEncoder().encodeToString(hash);

        if(!newHash.equals(user.getHashedPass())){
            // Generally shouldn't return error message, handled in controller
            throw new IllegalArgumentException("Wrong password");
        }
        // Success
        return 0;
    }


    @Transactional
    public void clearOldAccounts() throws Exception{
        Date date = new Date();
        Date oneYearAgo = new Date(date.getTime() - 365L * 24 * 60 * 60 * 1000);
        List<UserAccount> allUsers = userRepository.findAllUsersOlderThanDate(oneYearAgo);
        for (UserAccount user : allUsers) {
            userRepository.delete(user);
        }
    }
    
}