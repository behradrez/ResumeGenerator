package com.example.ResumeGeneratorBackend.service;

import java.util.Date;
import java.util.List;

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
    public void createUserAccount(String username) throws Exception{
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("User already exists by that username");
        }
        UserAccount user = new UserAccount(username);
        userRepository.save(user);
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