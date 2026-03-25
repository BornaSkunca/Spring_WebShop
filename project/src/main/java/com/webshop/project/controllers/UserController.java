package com.webshop.project.controllers;

import com.webshop.project.mapper.UserMapper;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.DTO.CreateUserRequest;
import com.webshop.project.DTO.UpdateUserRequest;
import com.webshop.project.DTO.UserDTO;
import com.webshop.project.models.User;
import com.webshop.project.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

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
    public UserDTO createUser(@Valid @RequestBody CreateUserRequest request){ 
        User user=this.userService.createUser(request);
        return userMapper.toDTO(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN','REGULAR')")
    @PutMapping("/users/{id}")
    public UserDTO updateUser(
        @Valid @RequestBody UpdateUserRequest request,
        @PathVariable Long id){
        User user=this.userService.updateUser(request, id);
        return userMapper.toDTO(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public User deleteUser(@RequestBody User user,@PathVariable Long id){
        return userService.deleteUser(user, id);
    }

}
