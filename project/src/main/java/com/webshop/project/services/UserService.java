package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public User createUser(User user){
        User newUser=this.userRepository.save(user);
        return newUser;
    }

    public User updateUser(User user,Long id){
        Optional<User> userOpt=this.userRepository.findById(id);

        if(!userOpt.isPresent()){
            return null;
        }

        User updatedUser=this.userRepository.save(userOpt.get());

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
