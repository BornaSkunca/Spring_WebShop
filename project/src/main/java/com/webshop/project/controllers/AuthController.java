package com.webshop.project.controllers;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.DTO.UserDTO;
import com.webshop.project.enums.Role;
import com.webshop.project.mapper.UserMapper;
import com.webshop.project.models.User;
import com.webshop.project.repositories.UserRepository;
import com.webshop.project.services.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    //All endpoints are public

    @PostMapping("/register")
    public UserDTO register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.REGULAR);

        User returnUser=userRepository.save(user);

        return userMapper.toDTO(returnUser);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody User loginReq){

        User user=userRepository.findByUsername(loginReq.getUsername()).orElseThrow();

        if(!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password!");
        }

        String token=jwtService.generateToken(user.getUsername());

        return Map.of("token",token);
    }
}
