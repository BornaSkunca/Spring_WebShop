package com.webshop.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
