package com.webshop.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.Cart;
import com.webshop.project.models.User;

public interface CartRepository extends JpaRepository<Cart,Long>{

    Optional<Cart> findByUser(User user);

}
