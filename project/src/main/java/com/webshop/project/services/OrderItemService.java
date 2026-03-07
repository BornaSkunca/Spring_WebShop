package com.webshop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.project.models.OrderItem;
import com.webshop.project.repositories.OrderItemRepository;


@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository){
        this.orderItemRepository=orderItemRepository;
    }

    public List<OrderItem> getAll(){
        return this.orderItemRepository.findAll();
    }

    public OrderItem getOrderItem(Long id){
        Optional<OrderItem> orderItemOpt=this.orderItemRepository.findById(id);

        if(!orderItemOpt.isPresent()){
            return null;
        }

        return orderItemOpt.get();
    }

    public OrderItem createOrderItem(OrderItem orderItem){
        OrderItem newOrderItem=this.orderItemRepository.save(orderItem);
        return newOrderItem;
    }

    public OrderItem updateOrderItem(OrderItem orderItem,Long id){
        Optional<OrderItem> orderItemOpt=this.orderItemRepository.findById(id);

        if(!orderItemOpt.isPresent()){
            return null;
        }

        OrderItem updatedOrderItem=this.orderItemRepository.save(orderItemOpt.get());

        return updatedOrderItem;
    }
}
