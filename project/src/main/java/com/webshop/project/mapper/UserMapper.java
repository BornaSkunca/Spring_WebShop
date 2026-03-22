package com.webshop.project.mapper;

import org.springframework.stereotype.Component;

import com.webshop.project.DTO.UserDTO;
import com.webshop.project.models.User;

@Component
public class UserMapper {

    public UserDTO toDTO(User user){
        if(user==null) return null;

        UserDTO dto=new UserDTO();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());

        return dto;
    }
}
