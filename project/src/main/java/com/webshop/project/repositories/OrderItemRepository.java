package com.webshop.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.project.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
