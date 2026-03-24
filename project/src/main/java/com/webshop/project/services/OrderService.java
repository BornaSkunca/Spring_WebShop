package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.project.exception.UnauthorizedException;
import com.webshop.project.models.Order;
import com.webshop.project.models.User;
import com.webshop.project.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public List<Order> getUserOrders(){
    return orderRepository.findByUser(userService.getCurrentUser());
    }

    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    public Order getOrder(Long id){
        Optional<Order> orderOpt=this.orderRepository.findById(id);

        if(!orderOpt.isPresent()){
            return null;
        }

        User user=userService.getCurrentUser();

        if (!orderOpt.get().getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You cannot access this order!");
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

    public Order deleteOrder(Order order,Long id){
        Optional<Order> orderOpt=orderRepository.findById(id);

        if(!orderOpt.isPresent()){
            return null;
        }

        orderRepository.delete(order);

        return order;
    }
}
