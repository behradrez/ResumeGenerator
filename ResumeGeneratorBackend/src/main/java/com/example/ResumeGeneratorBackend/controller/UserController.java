package com.example.ResumeGeneratorBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeGeneratorBackend.dto.UserAccountDTO;
import com.example.ResumeGeneratorBackend.service.UserService;

@CrossOrigin("")

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody UserAccountDTO accountDTO){
        try{
            userService.createUserAccount(accountDTO.getUsername(), accountDTO.getPass());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserAccountDTO accountDTO){
        try{
            userService.attemptLogin(accountDTO.getUsername(), accountDTO.getPass());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
