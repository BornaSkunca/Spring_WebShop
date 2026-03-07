package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.project.models.Order;
import com.webshop.project.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    public Order getOrder(Long id){
        Optional<Order> orderOpt=this.orderRepository.findById(id);

        if(!orderOpt.isPresent()){
            return null;
        }

        return orderOpt.get();
    }

    public Order createOrder(Order order){
        Order newOrder=this.orderRepository.save(order);
        return newOrder;
    }

    public Order updateOrder(Order order,Long id){
        Optional<Order> orderOpt=this.orderRepository.findById(id);

        if(!orderOpt.isPresent()){
            return null;
        }

        Order updatedOrder=this.orderRepository.save(orderOpt.get());

        return updatedOrder;
    }
}
