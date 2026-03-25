package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webshop.project.DTO.CreateUserRequest;
import com.webshop.project.DTO.UpdateUserRequest;
import com.webshop.project.enums.Role;
import com.webshop.project.exception.NotFoundException;
import com.webshop.project.models.User;
import com.webshop.project.repositories.UserRepository;
import com.webshop.project.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;
    private final PasswordEncoder passwordEncoder;
    

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getCurrentUser(){
        String username=securityUtils.getCurrentUsername();

        return userRepository.findByUsername(username)
        .orElseThrow(()->new NotFoundException("User not found!"));
    }

    public User getUserById(Long id){

        Optional<User> userOpt=this.userRepository.findById(id);

        if(!userOpt.isPresent()){
            return null;
        }
        return userOpt.get();
    }

    public User createUser(CreateUserRequest request){
        User newUser= new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setRole(Role.ADMIN);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        this.userRepository.save(newUser);
        return newUser;
    }

    public User updateUser(UpdateUserRequest request,Long id){
        Optional<User> userOpt=this.userRepository.findById(id);

        if(!userOpt.isPresent()){
            return null;
        }

        User updatedUser= new User();
        updatedUser.setEmail(request.getEmail());
        updatedUser.setUsername(request.getUsername());
        
        this.userRepository.save(updatedUser);

        return updatedUser;
    }

    public User deleteUser(User user,Long id){
        Optional<User> userOpt=userRepository.findById(id);

        if(!userOpt.isPresent()){
            return null;
        }

        userRepository.delete(user);

        return user;
    }

}
