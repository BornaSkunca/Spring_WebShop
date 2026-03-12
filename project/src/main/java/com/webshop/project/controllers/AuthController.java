package com.webshop.project.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.enums.Role;
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

    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.REGULAR);

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody User loginReq){
        User user=userRepository.findById(loginReq.getId()).orElseThrow();

        if(!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password!");
        }

        String token=jwtService.generateToken(user.getUsername());

        return Map.of("token",token);
    }
}
