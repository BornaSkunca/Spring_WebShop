package com.webshop.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.Order;
import com.webshop.project.models.User;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long>{
    public List<Order> findByUser(User user);
}
