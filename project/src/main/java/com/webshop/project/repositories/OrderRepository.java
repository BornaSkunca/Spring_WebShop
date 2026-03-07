package com.webshop.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
