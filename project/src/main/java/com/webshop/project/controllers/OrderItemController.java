package com.webshop.project.controllers;

import java.util.List;

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

    @GetMapping("/orderItems")
    public List<OrderItem> getAll(){
        return this.orderItemService.getAll();
    }

    @GetMapping("/orderItems/{id}")
    public OrderItem getOrderItem(@PathVariable Long id){
        return this.orderItemService.getOrderItem(id);
    }

    @PostMapping("/orderItems")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem){
        return this.orderItemService.createOrderItem(orderItem);
    }

    @PutMapping("/orderItems/{id}")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem,@PathVariable Long id){
        return this.orderItemService.updateOrderItem(orderItem, id);
    }

}
