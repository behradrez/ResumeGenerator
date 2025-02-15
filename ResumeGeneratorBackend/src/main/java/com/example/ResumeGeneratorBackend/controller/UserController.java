package com.example.ResumeGeneratorBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeGeneratorBackend.service.UserService;

@CrossOrigin("")

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/{username}/create")
    public ResponseEntity<?> createUser(@PathVariable String username){
        try{
            userService.createUserAccount(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
