package com.webshop.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.models.User;
import com.webshop.project.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return this.userService.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Long id){
        return this.userService.updateUser(user, id);
    }

}
