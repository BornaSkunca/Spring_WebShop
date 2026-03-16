package com.webshop.project.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.project.models.Order;
import com.webshop.project.services.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    //All endpoints require login

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orders")
    public List<Order> getAll(){
        return this.orderService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','REGULAR')")
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable Long id){
        return this.orderService.getOrder(id);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order){
        return this.orderService.createOrder(order);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PutMapping("/orders/{id}")
    public Order updateOrder(@RequestBody Order order,@PathVariable Long id){
        return this.orderService.updateOrder(order, id);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @DeleteMapping("/orders/{id}")
    public Order deleteOrder(@RequestBody Order order,@PathVariable Long id){
        return this.orderService.deleteOrder(order, id);
    }
}
