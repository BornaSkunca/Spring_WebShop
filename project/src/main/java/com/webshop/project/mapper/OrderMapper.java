package com.webshop.project.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webshop.project.DTO.OrderDTO;
import com.webshop.project.DTO.OrderItemDTO;
import com.webshop.project.models.Order;
import com.webshop.project.models.OrderItem;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order){
        if(order==null) return null;

        OrderDTO dto=new OrderDTO();

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus().name());

        List<OrderItemDTO> items=order.getOrderItems().stream().map(this::mapItem).toList();

        dto.setItems(items);

        return dto;
    }
    
    public OrderItemDTO mapItem(OrderItem orderItem){
        OrderItemDTO dto=new OrderItemDTO();

        dto.setPrice(orderItem.getPrice());
        dto.setProductId(orderItem.getId());
        dto.setProductName(orderItem.getProduct().getName());
        dto.setQuantity(orderItem.getQuantity());

        return dto;
    }
}
