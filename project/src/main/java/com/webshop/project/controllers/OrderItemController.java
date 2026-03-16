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

import com.webshop.project.models.OrderItem;
import com.webshop.project.services.OrderItemService;

@RestController
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService){
        this.orderItemService=orderItemService;
    }

    //All endpoints require login
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orderItems")
    public List<OrderItem> getAll(){
        return this.orderItemService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','REGULAR')")
    @GetMapping("/orderItems/{id}")
    public OrderItem getOrderItem(@PathVariable Long id){
        return this.orderItemService.getOrderItem(id);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PostMapping("/orderItems")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem){
        return this.orderItemService.createOrderItem(orderItem);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @PutMapping("/orderItems/{id}")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem,@PathVariable Long id){
        return this.orderItemService.updateOrderItem(orderItem, id);
    }

    @PreAuthorize("hasRole('REGULAR')")
    @DeleteMapping("/orderItems/{id}")
    public OrderItem deleteOrderItem(@RequestBody OrderItem orderItem,@PathVariable Long id){
        return orderItemService.deleteOrderItem(orderItem, id);
    }

}
