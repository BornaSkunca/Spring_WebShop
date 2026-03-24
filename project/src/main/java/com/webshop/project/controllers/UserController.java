package com.webshop.project.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.enums.Role;
import com.webshop.project.models.User;
import com.webshop.project.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    //All endpoints require Admin clearance

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> getAll(){
        return this.userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return this.userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return this.userService.createUser(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN','REGULAR')")
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Long id){
        return this.userService.updateUser(user, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public User deleteUser(@RequestBody User user,@PathVariable Long id){
        return userService.deleteUser(user, id);
    }

}
