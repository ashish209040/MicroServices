package com.microserivce.userservice.controllers;

import com.microserivce.userservice.entities.User;
import com.microserivce.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        User user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
