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

import com.webshop.project.DTO.OrderDTO;
import com.webshop.project.mapper.OrderMapper;
import com.webshop.project.models.Order;
import com.webshop.project.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    //All endpoints require login

    @GetMapping("/orders/my")
    @PreAuthorize("hasRole('REGULAR')")
    public List<OrderDTO> getMyOrders(){
        List<Order> orders=orderService.getUserOrders();
        return orders.stream().map(order->orderMapper.toDTO(order)).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orders")
    public List<OrderDTO> getAll(){
        List<Order> orders=this.orderService.getAll();
        return orders.stream().map(order->orderMapper.toDTO(order)).toList();
    }

    @PreAuthorize("hasAnyRole('ADMIN','REGULAR')")
    @GetMapping("/orders/{id}")
    public OrderDTO getOrder(@PathVariable Long id){
        Order order=this.orderService.getOrder(id);
        return orderMapper.toDTO(order);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PostMapping("/orders")
    public OrderDTO createOrder(@RequestBody Order order){
        Order returnOrder=this.orderService.createOrder(order);
        return orderMapper.toDTO(returnOrder);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PutMapping("/orders/{id}")
    public OrderDTO updateOrder(@RequestBody Order order,@PathVariable Long id){
        Order returnOrder=this.orderService.updateOrder(order, id);
        return orderMapper.toDTO(returnOrder);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @DeleteMapping("/orders/{id}")
    public OrderDTO deleteOrder(@RequestBody Order order,@PathVariable Long id){
        Order returnOrder=this.orderService.deleteOrder(order, id);
        return orderMapper.toDTO(returnOrder);
    }
}
